/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;

/**
 *
 * @author This PC
 */
public class RealTimeData {
    
    //current readings for each cell
    private double[] vCurr; //current voltage
    private double iCurr; //current current
    private int[] tCurr; //current temperature
    private int maxTemp;

    //an array of booleans indicating whether or not the cell 
    //at the specified index is bypassed or not
    private boolean[] isBypassed; 
    
    //an array of integers indicating how long the cell
    //at the specified index has been bypassed
    private long bypassTime[];
    
    //two dimension array representing the voltage difference between individual
    //cell pairs
    private double[][] diff;
    
    private boolean isCharging = false;
    //private int state = 0;
    private boolean done = false;
    private boolean chargingRelayOpen = true;
    private int cycleTime = 0;
    private int tick = 0;
    public enum State{IDLE, CHARGING};
    State state;
    
    private boolean errorOccurred;
    private String errorMessage;
    
    
    public RealTimeData(    double[] _vCurr, 
                            double _iCurr, 
                            int[] _tCurr, 
                            boolean[] _isBypassed,
                            long[] _bypassTime,
                            double[][] _diff,
                            boolean _isCharging,
                            State _state,
                            boolean _done,
                            boolean _chargingRelayOpen,
                            int _cycleTime,
                            int _tick, 
                            boolean _errorOccurred,
                            String _errorMessage)
    {
        this.vCurr = _vCurr;
        this.iCurr = _iCurr;
        this.tCurr = _tCurr;
        this.isBypassed = _isBypassed;
        this.bypassTime = _bypassTime;
        this.diff = _diff;
        this.isCharging = _isCharging;
        this.state = _state;
        this.done = _done;
        this.chargingRelayOpen = _chargingRelayOpen;
        this.cycleTime = _cycleTime;
        this.tick = _tick;
        this.errorOccurred = _errorOccurred;
        this.errorMessage = _errorMessage;
        
        maxTemp = tCurr[0];
        for (int i = 1; i < tCurr.length; i++)
        {
            if(tCurr[i] > maxTemp)
            {
                maxTemp = tCurr[i];
            }
        }
    }
    
    public void setVoltage(int index, double _vCurr)
    {
        this.vCurr[index] = _vCurr;
    }
    
    public double getVoltage(int index)
    {
        return this.vCurr[index];
    }
    
    public void setCurrent(double _iCurr)
    {
        this.iCurr = _iCurr;
    }
    
    public double getCurrent()
    {
        return this.iCurr;
    }
    
    public void setTemperature(int index, int _tCurr)
    {
        this.tCurr[index] = _tCurr;
        maxTemp = tCurr[0];
        for (int i = 1; i < tCurr.length; i++)
        {
            if(tCurr[i] > maxTemp)
            {
                maxTemp = tCurr[i];
            }
        }
    }
    
    public int getTemperature(int index)
    {
        return this.tCurr[index];
    }
    
    public int getMaxTemp()
    {
        return this.maxTemp;
    }
    
    public void setBypassInfo(int index, boolean _isBypassed)
    {
        this.isBypassed[index] = _isBypassed;
    }
    
    public boolean getBypassInfo(int index)
    {
        return this.isBypassed[index];
    }
    
    public void setBypassTime(int index, long time)
    {
        this.bypassTime[index] = time;
    }
    
    public long getBypassTime(int index)
    {
        return this.bypassTime[index];
    }
    
    /*
    public void setDiff(int index1, int index2, double diffValue)
    {
        this.diff[index1][index2] = diffValue;
    }
    */
    
    public double getDiff(int index1, int index2)
    {
        return (this.getVoltage(index1)-this.getVoltage(index2));
    }
    
    public void setIsCharging(boolean _isCharging)
    {
        this.isCharging = _isCharging;
    }
    
    public boolean getIsCharging()
    {
        return this.isCharging;
    }
    
    public void setState(State _state)
    {
        this.state = _state;
    }
    
    public State getState()
    {
        return this.state;
    }
    
    public void setDone(boolean _done)
    {
        this.done = _done;
    }
    
    public boolean getDone()
    {
        return this.done;
    }
    
    public void setChargingRelayOpen(boolean _chargingRelayOpen)
    {
        this.chargingRelayOpen = _chargingRelayOpen;
    }
    
    public boolean getChargingRelayOpen()
    {
        return this.chargingRelayOpen;
    }
    
    public void setCycleTime(int _cycleTime)
    {
        this.cycleTime = _cycleTime;
    }
    
    public int getCycleTime()
    {
        return this.cycleTime;
    }
    
    public void setTick(int _tick)
    {
        this.tick = _tick;
    }
    
    public int getTick()
    {
        return this.tick;
    }
    
    public boolean getErrorOccurred()
    {
        return this.errorOccurred;
    }
    
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    
    public void setErrorOccurred(boolean _errorOccurred)
    {
        this.errorOccurred = _errorOccurred;
    }
    
    public void setErrorMessage(String _errorMessage)
    {
        this.errorMessage = _errorMessage;
    }
}
