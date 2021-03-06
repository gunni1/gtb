package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PracticeService
{
    @Autowired
    private PracticeRepository practiceRepository;

    public List<PracticeDto> getLatestPractices(String userId, String practiceKey)
    {
        Optional<PracticeDto> maybeLastPractice = practiceRepository.findDistinctFirstByUserIdAndKeyOrderByPracticeTimeDescAllIgnoreCase(userId, practiceKey);
        if(maybeLastPractice.isPresent())
        {
            LocalDateTime actualDay = maybeLastPractice.get().getPracticeTime().truncatedTo(ChronoUnit.DAYS);
            LocalDateTime nextDay = actualDay.plusDays(1);
            return practiceRepository.findAllByUserIdAndKeyAndPracticeTimeBetweenAllIgnoreCase(userId, practiceKey, actualDay, nextDay);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public List<PracticeDto> getPractices(String userId, String practiceKey)
    {
        return practiceRepository.findByUserIdAndKeyAllIgnoreCase(userId, practiceKey);
    }

    public Set<String> getPracticeKeys(String userId)
    {
        List<PracticeDto> practices = practiceRepository.findByUserId(userId);
        return practices.stream().map(dto -> dto.getKey()).collect(Collectors.toSet());
    }

    public List<PracticeDto> getPracticesOfDay(String userId, LocalDate queryDate)
    {
        LocalDateTime begin = LocalDateTime.of(queryDate, LocalTime.MIDNIGHT);
        LocalDateTime end = begin.plusDays(1);
        return practiceRepository.findAllByUserIdAndPracticeTimeBetween(userId, begin, end);
    }
}
