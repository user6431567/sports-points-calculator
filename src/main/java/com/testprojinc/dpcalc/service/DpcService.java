package com.testprojinc.dpcalc.service;

import com.testprojinc.dpcalc.dto.CalculatedResultResponse;
import com.testprojinc.dpcalc.entity.Event;
import com.testprojinc.dpcalc.repository.EventRepository;
import com.testprojinc.dpcalc.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DpcService {

    private final EventRepository eventRepository;

    /**
     * Method to get list of sports available for points calculation
     *
     * @return list of all known decathlon sports
     */
    public List<String> getSportsList() {
        return eventRepository.findAll().stream()
                .map(Event::getName)
                .collect(Collectors.toList());
    }

    /**
     * Method to calculate decathlon points for given sport and result.
     *
     * @param sport Any decathlon event
     * @param result Time for track events, Distance for field events
     * @return ResultResponse with calculated decathlon points
     */
    public CalculatedResultResponse calculatePoints(String sport, String result) {
        final Event event = eventRepository.findByNameIgnoreCase(sport)
                .orElseThrow(() -> new IllegalArgumentException("Unknown sport: " + sport));

        return CalculatedResultResponse.builder()
                .sport(event.getName())
                .result(result)
                .points(calculateEventPoints(event, result))
                .build();
    }

    /**
     * Calculates points by event type
     *
     * @param event Any decathlon event
     * @param result Time for track events, Distance for field events
     * @return Calculated decathlon points
     */
    private int calculateEventPoints(Event event, String result) {
        switch (event.getType()) {
            case TRACK:
                return calculateTrackEventPoints(event, result);
            case FIELD:
                return calculateFieldEventPoints(event, result);
            default:
                throw new IllegalArgumentException("Unknown event type.");
        }
    }

    /**
     * Calculates points for track events
     *
     * @param event Decathlon track type event
     * @param result Time
     * @return Calculated decathlon points
     */
    private int calculateTrackEventPoints(Event event, String result) {
        final Double rb = event.getB() - TimeUtils.timeStringToDouble(result);
        return calculateCommonFormulaPart(event, rb);
    }

    /**
     * Calculates points for field events
     *
     * @param event Decathlon field type event
     * @param result Distance
     * @return Calculated decathlon points
     */
    private int calculateFieldEventPoints(Event event, String result) {
        final Double rb = (Double.parseDouble(result) * 100) - event.getB();
        return calculateCommonFormulaPart(event, rb);
    }

    /**
     * Calculates common formula part for all decathlon events
     *
     * @param event Any decathlon event
     * @param rb Calculated part with parameters 'result' and 'B'. Differs for field events and track events.
     * @return Calculated decathlon points
     */
    private int calculateCommonFormulaPart(Event event, Double rb) {
        return (int) (event.getA() * Math.pow(rb, event.getC()));
    }

}
