package gtbbackend.controller;

import gtbbackend.practice.PracticeService;
import gtbbackend.practice.dto.PracticeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class PracticeController
{
    @Autowired
    private PracticeService practiceService;

    @ApiOperation(value = "Get a list of all practices by a practices name of a specific user.", response = Iterable.class)
    @GetMapping("/practices/{userId}/{practiceKey}")
    public ResponseEntity<List<PracticeDto>> getPractices(@PathVariable String userId, @PathVariable String practiceKey)
    {
        return ResponseEntity.ok(practiceService.getPractices(userId, practiceKey));
    }

    @ApiOperation(value = "Get all entrys of a practice from the last day reported.", response = Iterable.class)
    @GetMapping("/practices/{userId}/{practiceKey}/latest")
    public ResponseEntity<List<PracticeDto>> getLatestPractices(@PathVariable String userId, @PathVariable String practiceKey)
    {
        return ResponseEntity.ok(practiceService.getLatestPractices(userId, practiceKey));

    }

    @ApiOperation(value = "Get all different practice keys a user.", response = Iterable.class)
    @GetMapping("/practices/{userId}")
    public ResponseEntity<Set<String>> getPracticeKeys(@PathVariable String userId)
    {
        return ResponseEntity.ok(practiceService.getPracticeKeys(userId));
    }




}
