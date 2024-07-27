package utility;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import model.ProductVariant;

public class ProductVariantSerializer implements JsonSerializer<ProductVariant> {

    @Override
    public JsonElement serialize(ProductVariant variant, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("productVariantId", variant.getProductVariantId());
        jsonObject.addProperty("size", variant.getSize());
        jsonObject.addProperty("color", variant.getColor());
        jsonObject.addProperty("price", variant.getPrice());
        jsonObject.addProperty("imageURL", variant.getImageURL());
        jsonObject.addProperty("stockQuantity", variant.getStockQuantity());

        // Optionally include minimal product details or omit entirely
        jsonObject.addProperty("productId", variant.getProduct().getProductId());

        return jsonObject;
    }
}

