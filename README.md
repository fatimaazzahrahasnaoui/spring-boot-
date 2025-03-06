# 📚 Projet Académique : Application Spring Boot & Angular

## 📝 Description du Projet
Ce projet académique est une **application web full-stack** développée avec **Spring Boot** pour le back-end et **Angular** pour le front-end. L'objectif principal est de présenter les concepts fondamentaux de chaque couche (Controller, Service, Repository) et d'implémenter des tests unitaires et d'intégration.

## 🚀 Fonctionnalités Principales

- **Gestion des entités :** Ajout, modification, suppression et consultation.
- **Connexion et authentification :** Sécurisation via Spring Security.
- **Validation des formulaires :** Contrôle des données avant insertion.
- **API REST :** Exposition des services sous forme d'API RESTful.
- **Interface Angular :** Consommation des API REST et affichage dynamique des données.
- **Tests :** Implémentation de tests unitaires avec JUnit et tests d'intégration avec MockMvc.
- **Base de données :** Intégration avec **MySQL** ou **H2** (environnement de test).

## 🛠️ Technologies Utilisées

- **Java** (JDK 17+)
- **Spring Boot** (3.x)
- **Spring Data JPA** (Gestion de la base de données)
- **Spring Security** (Sécurité et authentification)
- **Angular** (Framework front-end)
- **MySQL** (Base de données principale)
- **Maven** (Gestion des dépendances)
- **JUnit 5** (Tests unitaires)
- **MockMvc** (Tests d'intégration)

## 📂 Structure du Projet

### Back-End (Spring Boot)
```
spring-boot-project/
├── src/
│   ├── main/
│   │    ├── java/
│   │    │      └── com.example.project/
│   │    │              ├── controller/
│   │    │              ├── model/
│   │    │              ├── repository/
│   │    │              ├── service/
│   │    │              └── test/
│   │    └── resources/
│   │           ├── application.properties
│   │           └── templates/
└── pom.xml
```

### Front-End (Angular)
```
angular-client/
├── src/
│   ├── app/
│   │      ├── components/
│   │      ├── services/
│   │      └── models/
│   └── index.html
└── package.json
```

## 📊 Installation et Exécution

### Étape 1 : Cloner le dépôt
```bash
git clone https://github.com/fatimaazzahrahasnaoui/spring-boot-.git
cd spring-boot-
```

### Étape 2 : Configurer la base de données
Modifiez le fichier **application.properties** :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base
spring.datasource.username=votre_nom_utilisateur
spring.datasource.password=votre_mot_de_passe
```

### Étape 3 : Exécuter le back-end (Spring Boot)
```bash
mvn clean install
mvn spring-boot:run
```

### Étape 4 : Installer et exécuter le front-end (Angular)
```bash
cd angular-client
npm install
ng serve
```

### Étape 5 : Accéder à l'application
- **Frontend (Angular) :** [http://localhost:4200](http://localhost:4200)
- **API REST :** [http://localhost:8080/api](http://localhost:8080/api)



## 📌 Auteur
Projet réalisé par **Fatima Azzahra Hasnaoui**  **zaynab er-reghay**  **bilal lahfari**  **ayoub khezaz**  **sami malek** **hala lakhal**  dans le cadre d'un projet académique.  

## 📄 Licence
Ce projet est sous licence **MIT**. Vous êtes libre de le réutiliser avec attribution.

