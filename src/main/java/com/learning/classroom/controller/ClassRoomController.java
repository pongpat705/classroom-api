package com.learning.classroom.controller;

import com.learning.classroom.entity.ClassRoomEntity;
import com.learning.classroom.model.ResponseModel;
import com.learning.classroom.service.ClassRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassRoomController {

    private ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @RequestMapping(value = "/classroom", method = RequestMethod.GET)
    public ResponseModel<List<ClassRoomEntity>> getClassRoom(){

        return this.classRoomService.getClassRoom();
    }

    @RequestMapping(value = "/classroom/{id}", method = RequestMethod.GET)
    public ResponseModel<ClassRoomEntity> getClassRoomById(@PathVariable Long id){

        return this.classRoomService.getClassRoomById(id);
    }

    @RequestMapping(value = "/classroom/add", method = RequestMethod.POST)
    public ResponseModel<Void> addClassRoom(@RequestBody ClassRoomEntity classRoomEntity){

        return this.classRoomService.addClassRoom(classRoomEntity);
    }

    @RequestMapping(value = "/classroom/update", method = RequestMethod.POST)
    public ResponseModel<Void> updateClassRoom(@RequestBody ClassRoomEntity classRoomEntity){
        return this.classRoomService.updateClassRoom(classRoomEntity);
    }
}
