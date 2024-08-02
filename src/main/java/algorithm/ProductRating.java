package algorithm;

import java.util.*;

/**
 * 2. 在一个电商平台上，有很多用户和商品。每个用户可以对商品进行评价，评价分为好评和差评。现在需要你设计一个算法，根据用户的评价数据，计算每个商品的好评率，并找出好评率
 * 最高的前K个商品。
 * 解题思路：
 * 领域对象：用户/商品/评价
 * 边界：用户/商品/评价
 * 关联关系：用户 - 商品/商品 - 评价
 * 数据结构：map，put，get。
 * 算法：离线计算好评率，将所有商品放入数组，进行好评率从高到低的排序后截取前K个商品输出
 */
public class ProductRating {
    private int userId;
    private int productId;
    private String rating;
    private double ratingPercentage;

    public ProductRating(int userId, int productId, String rating) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
    }

    public ProductRating(int productId, double ratingPercentage) {
        this.productId = productId;
        this.ratingPercentage = ratingPercentage;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getRating() {
        return rating;
    }

    public double getRatingPercentage() {
        return ratingPercentage;
    }

    public static List<ProductRating> calculateProductRating(List<ProductRating> ratings) {
        Map<Integer, Integer> positiveRatings = new HashMap<>();
        Map<Integer, Integer> totalRatings = new HashMap<>();

        for (ProductRating rating : ratings) {
            int productId = rating.getProductId();
            if (!positiveRatings.containsKey(productId)) {
                positiveRatings.put(productId, 0);
                totalRatings.put(productId, 0);
            }
            if (rating.getRating().equals("好评")) {
                positiveRatings.put(productId, positiveRatings.get(productId)+1);
            }
            totalRatings.put(productId, totalRatings.get(productId)+1);
        }

        List<ProductRating> productRatingList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : positiveRatings.entrySet()) {
            int productId = entry.getKey();
            int positiveCount = entry.getValue();
            int totalCount = totalRatings.get(productId);
            double ratingPercentage = (double) positiveCount / totalCount;

            productRatingList.add(new ProductRating(productId, ratingPercentage));
        }
        return productRatingList;
    }

    public static List<ProductRating> findTopKProducts(List<ProductRating> productRatingList, int k) {
        productRatingList.sort(Comparator.comparingDouble(ProductRating::getRatingPercentage).reversed());
        return productRatingList.subList(0, k);
    }

    public static void main(String[] args) {
        List<ProductRating> ratings = new ArrayList<>();
        ratings.add(new ProductRating(1,1,"好评"));
        ratings.add(new ProductRating(2, 1, "差评"));
        ratings.add(new ProductRating(3, 2, "好评"));
        ratings.add(new ProductRating(4, 2, "好评"));
        ratings.add(new ProductRating(5, 3, "差评"));
        ratings.add(new ProductRating(6, 3, "好评"));
        ratings.add(new ProductRating(7, 3, "差评"));

        List<ProductRating> productRatingList = calculateProductRating(ratings);
        List<ProductRating> topKProducts = findTopKProducts(productRatingList, 2);
        for (ProductRating product : topKProducts) {
            System.out.println("商品ID: " + product.getProductId() + ", 好评率: " + product.getRatingPercentage());
        }
    }
}
