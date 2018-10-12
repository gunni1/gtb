package gtbbackend.controller;

import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class PracticeController
{
    @Autowired
    private PracticeRepository practiceRepository;

    @ApiOperation(value = "Get a list of all practices by a practices name of a specific user ", response = Iterable.class)
    @GetMapping("/practices/{userId}/{practiceKey}")
    public ResponseEntity<List<PracticeDto>> getPractices(@PathVariable String userId, @PathVariable String practiceKey)
    {
        List<PracticeDto> usersPractices = practiceRepository.findByUserIdAndKey(userId, practiceKey);
        return ResponseEntity.ok(usersPractices);
    }


    @ApiOperation(value = "Get all different practice keys a user ", response = Iterable.class)
    @GetMapping("/practices/{userId}")
    public ResponseEntity<List<String>> getPracticeKeys(@PathVariable String userId)
    {
        List<PracticeDto> practices = practiceRepository.findByUserId(userId);
        Set<String> practiceKeys = practices.stream().map(dto -> dto.getKey()).collect(Collectors.toSet());
        return ResponseEntity.ok(new ArrayList<>(practiceKeys));
    }


}
