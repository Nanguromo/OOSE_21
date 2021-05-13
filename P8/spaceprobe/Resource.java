package edu.curtin.spaceprobe;

import java.util.Collection;

//public interface Resource
public interface Resource<R>
{
    //void useUp(Object amount);
    public void useUp(R amount);
    public R getRemaining();
    public long getTime(long elapsedTime);
}

//Generic R. Each Resource Implementation specifies what R is, hence, allowing for each resource won't need to type-cast what type an object is
//For example, in FuelTank, we specify implementation of "implements Resource<FuelAmount>", hence, R is now FuelAmount objects
//This ensures type safety, so we do not need to check what type of downcast from Object to FuelAmount.