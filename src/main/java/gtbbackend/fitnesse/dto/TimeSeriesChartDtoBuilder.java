package gtbbackend.fitnesse.dto;

import java.util.ArrayList;
import java.util.List;

public class TimeSeriesChartDtoBuilder
{
    private final String xCaption;
    private final String yCaption;
    private List<Long> xTimeValues;
    private List<Double> yValues;

    public TimeSeriesChartDtoBuilder(String xCaption, String yCaption)
    {
        this.xCaption = xCaption;
        this.yCaption = yCaption;
        this.yValues = new ArrayList<>();
        this.xTimeValues = new ArrayList<>();
    }


    public TimeSeriesChartDtoBuilder withPoint(Long xTimeMillis, Double yWeightValue)
    {
        xTimeValues.add(xTimeMillis);
        yValues.add(yWeightValue);
        return this;
    }

    public TimeSeriesChartDto build()
    {

        return new TimeSeriesChartDto().setyCaption(yCaption).setxCaption(xCaption)
                .setxTimeMillis(xTimeValues.toArray(new Long[xTimeValues.size()]))
                .setyValues(yValues.toArray(new Double[yValues.size()]));
    }
}
