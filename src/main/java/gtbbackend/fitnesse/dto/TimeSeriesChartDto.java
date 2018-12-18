package gtbbackend.fitnesse.dto;

public class TimeSeriesChartDto
{
    String xCaption;
    String yCaption;

    Long[] xTimeMillis;
    Double[] yValues;

    public TimeSeriesChartDto()
    {
    }

    public String getxCaption()
    {
        return xCaption;
    }

    public TimeSeriesChartDto setxCaption(String xCaption)
    {
        this.xCaption = xCaption;
        return this;
    }

    public String getyCaption()
    {
        return yCaption;
    }

    public TimeSeriesChartDto setyCaption(String yCaption)
    {
        this.yCaption = yCaption;
        return this;
    }

    public Long[] getxTimeMillis()
    {
        return xTimeMillis;
    }

    public TimeSeriesChartDto setxTimeMillis(Long[] xTimeMillis)
    {
        this.xTimeMillis = xTimeMillis;
        return this;
    }

    public Double[] getyValues()
    {
        return yValues;
    }

    public TimeSeriesChartDto setyValues(Double[] yValues)
    {
        this.yValues = yValues;
        return this;
    }
}
