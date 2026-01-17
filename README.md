# NMK Footwear — Java Web E‑Commerce

A JSP/Servlet-based footwear store with JPA-backed persistence. Browse products and variants, manage a session cart, apply promotions, place orders, and handle user accounts — packaged as a Maven webapp for Tomcat.

## Features

- Products: list and detail views with variants and pricing
- Cart: session cart with add/update/remove items
- Checkout: delivery info and payment selection
- Orders: placement and order detail tracking
- Account: registration, login, and profile management
- Promotions: rule-based discounts applied to cart/products

## Tech Stack

- Java, JSP/Servlets (Jakarta EE), JPA
- Maven for build and dependency management
- Tomcat application server
- MSSQL as RDBMS

## Project Layout

- Build: [NMK_footwear/nmk_footwear_website/pom.xml](nmk_footwear_website/pom.xml)
- Persistence: [NMK_footwear/nmk_footwear_website/src/main/resources/META-INF/persistence.xml](nmk_footwear_website/src/main/resources/META-INF/persistence.xml)
- Controllers: [NMK_footwear/nmk_footwear_website/src/main/java/controller](nmk_footwear_website/src/main/java/controller)
- DAO / Entities: [NMK_footwear/nmk_footwear_website/src/main/java/dao](nmk_footwear_website/src/main/java/dao), [NMK_footwear/nmk_footwear_website/src/main/java/model](nmk_footwear_website/src/main/java/model)
- Views: [NMK_footwear/nmk_footwear_website/src/main/webapp/jsp](nmk_footwear_website/src/main/webapp/jsp)
- Web config: [NMK_footwear/nmk_footwear_website/src/main/webapp/WEB-INF/web.xml](nmk_footwear_website/src/main/webapp/WEB-INF/web.xml)
