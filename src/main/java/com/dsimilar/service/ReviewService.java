package com.dsimilar.service;

import java.util.List;

import com.dsimilar.exception.ProductException;
import com.dsimilar.model.Review;
import com.dsimilar.model.User;
import com.dsimilar.request.ReviewRequest;

public interface ReviewService {
	
	public Review createReview(ReviewRequest req, User user) throws ProductException;
	public List<Review> getAllReview(Long productId);
}
