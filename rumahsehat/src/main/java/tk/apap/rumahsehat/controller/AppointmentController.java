package tk.apap.rumahsehat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;
    //buat contoh

    @GetMapping("/course/add")
    public String addAppointmentFormPage(Model model) {
          AppointmentModel appointment = new AppointmentModel();
//        List<PengajarModel> listPengajar = pengajarService.getListPengajar();
//        List<PengajarModel> listPengajarNew = new ArrayList<>(); //why
//
//        List<PenyelenggaraModel> listPenyelenggara = penyelenggaraService.getListPenyelenggara();
//        List<PenyelenggaraModel> listPenyelenggaraNew = new ArrayList<>(); //why
//
//        course.setListPengajar(listPengajarNew);
//        course.getListPengajar().add(new PengajarModel());
//
//        course.setListPenyelenggara(listPenyelenggaraNew);
//        course.getListPenyelenggara().add(new PenyelenggaraModel());

        model.addAttribute("course", new AppointmentModel());
        //model.addAttribute("listPengajarExisting", listPengajar);
        //model.addAttribute("listPenyelenggaraExisting", listPenyelenggara);
        return "form-add-course";
    }


    @PostMapping(value="/appointment/add", params={"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model) {
//        if(course.getListPenyelenggara()==null) {
//            course.setListPenyelenggara(new ArrayList<>());
//        }
//        AppointmentService.add
//        System.out.println(course.getCode());
//        model.addAttribute("code", course.getCode());
        return "add-course";
    }


    @GetMapping("/appointment/viewAll")
    public String listAppointment(Model model) {
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment";
    }

    @GetMapping("/appointment/view")
    public String viewDetailCoursePage(@RequestParam(value = "code") String code, Model model) {
//        AppointmentModel appointment = appointmentService.getCourseByCodeCourse(code);
//        List<PengajarModel> listPengajar = course.getListPengajar();
//        model.addAttribute("listCourse", listPengajar);
//        model.addAttribute("course", course);
        return "appointment-details";
    }

//    @GetMapping("/course/view-query")
//    public String viewDetailCoursePageQuery(@RequestParam(value = "code") String code, Model model) {
//        CourseModel course = courseService.getCourseByCodeCourseQuery(code);
//        List<PengajarModel> listPengajar = course.getListPengajar();
//        model.addAttribute("listPengajar", listPengajar);
//        model.addAttribute("course", course);
//        return "view-course";
//    }
//
//    @GetMapping("/course/update/{code}")
//    public String updateCourseFormPage(@PathVariable String code, Model model) {
//        CourseModel course = courseService.getCourseByCodeCourse(code);
//        model.addAttribute("course", course);
//        return "form-update-course";
//    }
//
//    @PostMapping("/course/update")
//    public String updateCourseSubmitPage(@ModelAttribute CourseModel course, Model model) {
//        CourseModel updatedCourse = courseService.updateCourse(course);
//        model.addAttribute("course", updatedCourse.getCode());
//        return "update-course";
//    }


    // latihan no 4
//    @GetMapping("/course/delete/{code}")
//    public String deleteCourse(@PathVariable String code, Model model){
//        CourseModel course = courseService.getCourseByCodeCourse(code);
//        LocalDateTime sekarang = LocalDateTime.now();
//
//        if ((sekarang.isBefore(course.getTanggalBerakhir()) && sekarang.isAfter(course.getTanggalDimulai())) || course.getListPengajar().size() != 0){
//            return "gagal-delete-course";
//        }
//        else {
//            courseService.deleteCourse(course);
//            return "delete-course";
//        }
//
//    }
//
//    @PostMapping(value="/course/add", params={"addRowPengajar"})
//    private String addRowPengajarMultiple(
//            @ModelAttribute CourseModel course,
//            Model model
//    ) {
//        if(course.getListPengajar()==null || course.getListPengajar().size()==0) {
//            course.setListPengajar(new ArrayList<>());
//        }
//        course.getListPengajar().add(new PengajarModel());
//        List<PengajarModel> listPengajar = pengajarService.getListPengajar();
//
//        model.addAttribute("course", course);
//        model.addAttribute("listPengajarExisting", listPengajar);
//
//        return "form-add-course";
//    }
//
//    @PostMapping(value="/course/add", params={"deleteRowPengajar"})
//    private String deleteRowPengajarMultiple(
//            @ModelAttribute CourseModel course,
//            @RequestParam("deleteRowPengajar") Integer row,
//            Model model
//    ) {
//        final Integer rowId = Integer.valueOf(row);
//        //course.getListPengajar().remove(rowId.intValue());
//        course.getListPengajar().remove(rowId.intValue());
//
//        List<PengajarModel> listPengajar = pengajarService.getListPengajar();
//
//        model.addAttribute("course", course);
//        model.addAttribute("listPengajarExisting", listPengajar);
//
//        return "form-add-course";
//    }
//
//    @PostMapping(value="/course/add", params={"addRow"})
//    private String addRowPenyelenggaraMultiple(
//            @ModelAttribute CourseModel course,
//            Model model
//    ) {
//        if(course.getListPenyelenggara()==null || course.getListPenyelenggara().size()==0) {
//            course.setListPenyelenggara(new ArrayList<>());
//        }
//        course.getListPenyelenggara().add(new PenyelenggaraModel());
//        List<PenyelenggaraModel> listPenyelenggara = penyelenggaraService.getListPenyelenggara();
//
//        model.addAttribute("course", course);
//        model.addAttribute("listPenyelenggaraExisting", listPenyelenggara);
//
//        return "form-add-course";
//    }
//
//    @PostMapping(value="/course/add", params={"deleteRow"})
//    private String deleteRowPenyelenggaraMultiple(
//            @ModelAttribute CourseModel course,
//            @RequestParam("deleteRow") Integer row,
//            Model model
//    ) {
//        final Integer rowId = Integer.valueOf(row);
//        course.getListPenyelenggara().remove(rowId.intValue());
//
//        List<PenyelenggaraModel> listPenyelenggara = penyelenggaraService.getListPenyelenggara();
//
//        model.addAttribute("course", course);
//        model.addAttribute("listPenyelenggaraExisting", listPenyelenggara);
//
//        return "form-add-course";
//    }


}
