//package kz.project.one.controller;
//
//import kz.project.one.Example;
//import kz.project.one.Service.ServiceFeignClient;
//import kz.project.one.model.UserStatisticModel;
//import org.hibernate.jmx.StatisticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping( "/users/{id}/statistic")
//public class StatisticsController {
//
//    @Autowired
//    ServiceFeignClient serviceFeignClient;
//
//    public List<Example> getStatistic(@PathVariable String id) {
//        List<Example> statisticsList = albumsService.getAlbums(id);
//        return statisticsList;
//    }
//}