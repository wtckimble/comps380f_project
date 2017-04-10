package edu.ouhk.comps380f.controller;

import edu.ouhk.comps380f.dao.LectureRepository;
import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.view.DownloadingView;
import java.io.IOException;
import java.sql.Statement;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@Repository
@RequestMapping("lecture")
public class LectureController {
    @Autowired
    public DataSource dataSource;
    public Statement stmt;
    
    @Autowired
    LectureRepository lectureRepo;
    
    private volatile long TICKET_ID_SEQUENCE = 1;
    private Map<Long, Lecture> ticketDatabase = new LinkedHashMap<>();

    @RequestMapping(value = {"", "lecture"}, method = RequestMethod.GET)
    public ModelAndView list(Principal principal) {
        ModelAndView mav = new ModelAndView("lecture");
        mav.addObject("lecturelist", lectureRepo.findAll());
        //mav.addObject("username", principal.getName());
        return mav;
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("ticketId") long ticketId) {
        Lecture ticket = this.ticketDatabase.get(ticketId);
        if (ticket == null) {
            return new ModelAndView(new RedirectView("/ticket/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("ticketId", Long.toString(ticketId));
        modelAndView.addObject("ticket", ticket);
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("add", "ticketForm", new Form());
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
    public View create(Form form, Principal principal) {
        Lecture ticket = new Lecture();
        ticket.setCustomerName(principal.getName());
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());
        
        /*for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                ticket.addAttachment(attachment);
            }
        }*/
        lectureRepo.create(ticket);

        /*this.ticketDatabase.put(ticket.getId(), ticket);*/
        return new RedirectView("/lecture/view/" + ticket.getId(), true);
    }

    private synchronized long getNextTicketId() {
        return this.TICKET_ID_SEQUENCE++;
    }

    @RequestMapping(
            value = "/{ticketId}/attachment/{attachment:.+}",
            method = RequestMethod.GET
    )
    
    
    
    public View download(@PathVariable("ticketId") long ticketId,
            @PathVariable("attachment") String name) {
        Lecture ticket = this.ticketDatabase.get(ticketId);
        if (ticket != null) {
            Attachment attachment = ticket.getAttachment(name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }
        }
        return new RedirectView("/ticket/list", true);
    }

}
