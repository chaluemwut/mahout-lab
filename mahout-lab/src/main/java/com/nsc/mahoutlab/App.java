package com.nsc.mahoutlab;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			DataModel model = new FileDataModel(
					new File("/home/off/git/mahout-lab/mahout-lab/src/main/java/intro.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,
					similarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model,
					neighborhood, similarity);
			List<RecommendedItem> recommendedItems = recommender
					.recommend(1, 1);
			for (RecommendedItem recommendedItem : recommendedItems) {
				System.out.println(recommendedItem);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
}
