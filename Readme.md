# DreamShops - Spring Boot E-Commerce API

DreamShops is a Spring Boot-based e-commerce backend application. It provides RESTful APIs for managing products, categories, carts, cart items, and images.

## Features

- Product management (CRUD, search, filter)
- Category management
- Cart and cart item management
- Image upload and download for products
- OpenAPI/Swagger documentation

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

### Products

| Method | Endpoint                                             | Description                      |
| ------ | ---------------------------------------------------- | -------------------------------- |
| GET    | `/api/v1/products`                                   | Get all products                 |
| GET    | `/api/v1/products/product/{id}`                      | Get product by ID                |
| DELETE | `/api/v1/products/product/{id}`                      | Delete product by ID             |
| POST   | `/api/v1/products/product/add`                       | Add a new product                |
| PUT    | `/api/v1/products/product/{productId}`               | Update product by ID             |
| GET    | `/api/v1/products/category/{category}`               | Get products by category         |
| GET    | `/api/v1/products/brand/{brand}`                     | Get products by brand            |
| GET    | `/api/v1/products/category/{category}/brand/{brand}` | Get products by category & brand |
| GET    | `/api/v1/products/brand/{brand}/name/{name}`         | Get products by brand & name     |
| GET    | `/api/v1/products/name/{name}`                       | Get products by name             |
| GET    | `/api/v1/products/count/brand/{brand}/name/{name}`   | Count products by brand & name   |

### Categories

| Method | Endpoint                                  | Description           |
| ------ | ----------------------------------------- | --------------------- |
| GET    | `/api/v1/categories`                      | Get all categories    |
| POST   | `/api/v1/categories/add`                  | Add a new category    |
| GET    | `/api/v1/categories/category/{id}`        | Get category by ID    |
| GET    | `/api/v1/categories/category/name/{name}` | Get category by name  |
| DELETE | `/api/v1/categories/category/{id}`        | Delete category by ID |
| PUT    | `/api/v1/categories/category/{id}`        | Update category by ID |

### Cart

| Method | Endpoint                | Description               |
| ------ | ----------------------- | ------------------------- |
| POST   | `/api/v1/cart`          | Create a new cart         |
| GET    | `/api/v1/cart`          | Get all carts             |
| GET    | `/api/v1/cart/{cartId}` | Get cart by ID            |
| DELETE | `/api/v1/cart/{cartId}` | Clear (delete) cart by ID |

### Cart Items

| Method | Endpoint                                                                       | Description                  |
| ------ | ------------------------------------------------------------------------------ | ---------------------------- |
| GET    | `/api/v1/cartItem`                                                             | Get all cart items           |
| POST   | `/api/v1/cartItem?cartId={cartId}&productId={productId}&quantity={quantity}`   | Add item to cart             |
| DELETE | `/api/v1/cartItem?cartId={cartId}&cartItemId={cartItemId}`                     | Remove item from cart        |
| PUT    | `/api/v1/cartItem?cartId={cartId}&cartItemId={cartItemId}&quantity={quantity}` | Update item quantity in cart |

### Images

| Method | Endpoint                                      | Description                 |
| ------ | --------------------------------------------- | --------------------------- |
| GET    | `/api/v1/images`                              | Get all images              |
| POST   | `/api/v1/images/upload?productId={productId}` | Upload images for a product |
| GET    | `/api/v1/images/image/download/{imageId}`     | Download image by ID        |
| PUT    | `/api/v1/images/image/update/{imageId}`       | Update image by ID          |
| DELETE | `/api/v1/images/image/delete/{imageId}`       | Delete image by ID          |

---

## Error Handling

- Returns JSON error responses with appropriate HTTP status codes.
- Custom error pages for 404 (not found).

---

## License

This project is licensed under the Apache License 2.0.

---

## Author

- Vimal Verma
