package com.dsimilar.service;

import java.util.List;

import com.dsimilar.exception.ProductException;
import com.dsimilar.model.Rating;
import com.dsimilar.model.User;
import com.dsimilar.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user)throws ProductException;
	public List<Rating>getProductsRating(Long productId);
}
