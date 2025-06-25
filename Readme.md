# DreamShops - Spring Boot E-Commerce API

DreamShops is a Spring Boot-based e-commerce backend application. It provides RESTful APIs for managing users, products, categories, carts, cart items, orders, and images.

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL (or H2 for testing)

### Running the Application

```sh
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/api/v1/`

Swagger UI: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

---

## API Endpoints

### Auth

| Method | Endpoint             | Description         |
| ------ | -------------------- | ------------------- |
| POST   | `/api/v1/auth/login` | User login          |
| POST   | `/api/v1/auth/register` | User registration |

### Users

| Method | Endpoint                     | Description         |
| ------ | ---------------------------- | ------------------- |
| POST   | `/api/v1/user`               | Add a new user      |
| GET    | `/api/v1/user`               | Get all users       |
| GET    | `/api/v1/user/{userId}`      | Get user by ID      |
| PUT    | `/api/v1/user/{userId}`      | Update user by ID   |
| DELETE | `/api/v1/user/{userId}`      | Delete user by ID   |
| GET    | `/api/v1/user/cart/{userId}` | Get cart by user ID |

### Products

| Method | Endpoint                                             | Description                      |
| ------ | ---------------------------------------------------- | -------------------------------- |
| POST   | `/api/v1/products/product/add`                       | Add a new product                |
| GET    | `/api/v1/products`                                   | Get all products                 |
| GET    | `/api/v1/products/product/{productId}`               | Get product by ID                |
| PUT    | `/api/v1/products/product/{productId}`               | Update product by ID             |
| DELETE | `/api/v1/products/product/{productId}`               | Delete product by ID             |
| GET    | `/api/v1/products/name/{name}`                       | Get products by name             |
| GET    | `/api/v1/products/count/brand/{brand}/name/{name}`   | Count products by brand & name   |
| GET    | `/api/v1/products/category/{category}`               | Get products by category         |
| GET    | `/api/v1/products/category/{category}/brand/{brand}` | Get products by category & brand |
| GET    | `/api/v1/products/brand/{brand}`                     | Get products by brand            |

### Categories

| Method | Endpoint                                   | Description           |
| ------ | ------------------------------------------ | --------------------- |
| POST   | `/api/v1/categories/add`                   | Add a new category    |
| GET    | `/api/v1/categories`                       | Get all categories    |
| GET    | `/api/v1/categories/category/{categoryId}` | Get category by ID    |
| PUT    | `/api/v1/categories/category/{categoryId}` | Update category by ID |
| DELETE | `/api/v1/categories/category/{categoryId}` | Delete category by ID |
| GET    | `/api/v1/categories/category/name/{name}`  | Get category by name  |

### Cart

| Method | Endpoint                | Description               |
| ------ | ----------------------- | ------------------------- |
| POST   | `/api/v1/cart`          | Create a new cart         |
| GET    | `/api/v1/cart`          | Get all carts             |
| GET    | `/api/v1/cart/{cartId}` | Get cart by ID            |
| DELETE | `/api/v1/cart/{cartId}` | Clear (delete) cart by ID |

### Cart Items

| Method | Endpoint           | Description                                                                         |
| ------ | ------------------ | ----------------------------------------------------------------------------------- |
| GET    | `/api/v1/cartItem` | Get all cart items                                                                  |
| POST   | `/api/v1/cartItem` | Add item to cart (`cartId`, `productId`, `quantity` as request params)              |
| PUT    | `/api/v1/cartItem` | Update item quantity in cart (`cartId`, `cartItemId`, `quantity` as request params) |
| DELETE | `/api/v1/cartItem` | Remove item from cart (`cartId`, `cartItemId` as request params)                    |

### Orders

| Method | Endpoint                   | Description          |
| ------ | -------------------------- | -------------------- |
| POST   | `/api/v1/orders/{userId}`  | Place order for user |
| GET    | `/api/v1/orders/{orderId}` | Get order by ID      |

### Images

| Method | Endpoint                                      | Description                                       |
| ------ | --------------------------------------------- | ------------------------------------------------- |
| POST   | `/api/v1/images/upload`                       | Upload images for a product (multipart/form-data) |
| GET    | `/api/v1/images`                              | Get all images                                    |
| GET    | `/api/v1/images/image/download/{imageId}`     | Download image by ID                              |
| PUT    | `/api/v1/images/image/update/{imageId}`       | Update image by ID (multipart/form-data)          |
| DELETE | `/api/v1/images/image/delete/{imageId}`       | Delete image by ID                                |

---

## Error Handling

- Returns JSON error responses with appropriate HTTP status codes.
- Custom error pages for 404 (not found).
