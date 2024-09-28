package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Train;
import jakarta.validation.ValidationException;

public class TrainValidate 
{
	public void validate(Train train)
	{
		validateTrainname(train.getTrainname());
	    validateCoachcount(train.getCoachcount());
	    validateDescription(train.getDescription());	
	}
	
	private void validateTrainname(String trainname)
	{
		if (trainname == null || trainname.isEmpty())
		{
            throw new ValidationException("Trainname cannot be empty");
        }
	}
	
	private void validateDescription(String description)
	{
		if (description == null || description.isEmpty())
		{
            throw new ValidationException("Description cannot be empty");
        }
	}
	
	private void validateCoachcount(int coachcount)
	{
	    if (coachcount <= 0) 
	    {
	        throw new ValidationException("Coachcount must be a positive number");
	    }
	    if (coachcount > 50)
	    {
	        throw new ValidationException("Coachcount cannot exceed 50");
	    }
	}
}
