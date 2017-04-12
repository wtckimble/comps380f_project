package edu.ouhk.comps380f.controller;

import edu.ouhk.comps380f.dao.AttachmentRepository;
import edu.ouhk.comps380f.dao.OtherRepository;
import edu.ouhk.comps380f.dao.ReplyRepository;
import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Other;
import edu.ouhk.comps380f.model.Reply;
import edu.ouhk.comps380f.view.DownloadingView;
import java.io.IOException;
import java.sql.Statement;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Repository
@RequestMapping("other")
public class OtherController {

    @Autowired
    public DataSource dataSource;
    public Statement stmt;

    @Autowired
    OtherRepository otherRepo;

    @Autowired
    ReplyRepository replyRepo;

    @Autowired
    AttachmentRepository attachmentRepo;

    private volatile long TICKET_ID_SEQUENCE = 1;
    private Map<Long, Other> ticketDatabase = new LinkedHashMap<>();

    @RequestMapping(value = {"", "other"}, method = RequestMethod.GET)
    public ModelAndView list(Principal principal) {
        ModelAndView mav = new ModelAndView("other");
        mav.addObject("otherlist", otherRepo.findAll());
        //mav.addObject("username", principal.getName());
        return mav;
    }

    @RequestMapping(value = "reply/{ticketId}", method = RequestMethod.GET)
    public ModelAndView reply(@PathVariable("ticketId") int ticketId) {
        System.out.println(ticketId);
        ModelAndView modelAndView = new ModelAndView("reply");
        modelAndView.addObject("replyForm", new replyForm());
        modelAndView.addObject("ticketId", ticketId);
        return modelAndView;

    }

    @RequestMapping(value = "view3/{ticketId}", method = RequestMethod.GET)
    public ModelAndView view3(@PathVariable("ticketId") int ticketId) {

        ModelAndView modelAndView = new ModelAndView("view3");
        modelAndView.addObject("otherInfo", otherRepo.findByOtherId(ticketId));
        modelAndView.addObject("replylist", replyRepo.findByTopicId(ticketId));
        return modelAndView;
    }
    
    

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("add", "ticketForm", new Form());
    }
    
    
    @RequestMapping(value = "view3/{otherId}/deleteReply/{replyId}", method = RequestMethod.GET)
    public View deleteReply(@PathVariable("replyId") int replyId, @PathVariable("otherId") int otherId) {
        replyRepo.deleteByReplyId(replyId);
        return new RedirectView("/other/view3/{otherId}", true);
    }
    
    
    @RequestMapping(value = "reply/{ticketId}", method = RequestMethod.POST)
    public View reply(@PathVariable("ticketId") int ticketId, replyForm form, Principal principal) {
        Reply reply = new Reply();
        reply.setCustomerName(principal.getName());
        reply.setTopicId(ticketId);
        reply.setBody(form.getBody());
        /*
        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                ticket.addAttachment(attachment);
            }*/
        replyRepo.createReply(reply);

        //this.ticketDatabase.put(ticket.getId(), ticket);
        return new RedirectView("/other/view3/" + ticketId, true);
    }

    public static class replyForm {

        private String body;
        private String customerName;
        private List<MultipartFile> attachments;
        private int topicId;

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    public static class Form {

        private String customerName;
        private String subject;
        private String body;
        private List<MultipartFile> attachments;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View create(Form form, Principal principal) throws IOException {
        Other ticket = new Other();
        ticket.setCustomerName(principal.getName());
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());

        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                ticket.addAttachment(attachment);
            }

            int topicId = otherRepo.createOther(ticket);
            otherRepo.createAttachment(ticket, topicId);
            /*System.out.println(attachment.getName());
            System.out.println(attachment.getContents());
            System.out.println(attachment.getMimeContentType());*/

 /*this.ticketDatabase.put(ticket.getId(), ticket);*/
        }
        return new RedirectView("/other", true);
    }

    private synchronized long getNextTicketId() {
        return this.TICKET_ID_SEQUENCE++;
    }

    @RequestMapping(value = "download/{ticketId}/attachment/{attachment:.+}", method = RequestMethod.GET )
    public View download(@PathVariable("ticketId") int ticketId, @PathVariable("attachment") String name) {
        Other other = otherRepo.findByOtherId(ticketId);
        if (other != null) {
            Attachment attachment = other.getAttachment(name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }
        }
        return new RedirectView("/ticket/list", true);
    }

}
