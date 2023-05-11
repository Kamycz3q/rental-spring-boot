### Wypożyczalnia pojazdów

# Struktura

User
- Dodawanie i usuwanie użytkownika
- modyfikacja emailu, imienia, nazwiska
- historia wypozyczen pojazdow

Pojazd
- marka
- model
- rocznik
- nr. Tablicy rejestracyjnej
- kategoria


Wypożyczenie
- okres
- dodanie i usunięcie wypożyczenia
- dla jakiego użytkownika
- jaki pojazd
- czy jest aktywne (opcjonalnie)
- status wypozyczenia
- czy jest opłacone?

# API Endpoints
> Format: METHOD ENDPOINT

---
## User

`GET` `/api/v1/users` -> **Returns users list**

**Example response**
```json
[
  {
    "id": 1,
    "name": "Kamil",
    "surname": "Test",
    "email": "test@test.com"
  }
]
```
---
`GET` `/api/v1/users/{userId}` -> **Returns user object**

**Example response**
```json
{
    "id": 1,
    "name": "Kamil",
    "surname": "Gaweł",
    "email": "test@test.com"
}
```
---
`POST` `/api/v1/users` -> **Creates user with request body and returns new user object**

**Request body**
```json
{
    "name": "user_name",
    "surname": "user_surname",
    "email": "user_e-mail_address"
}
```
**Example response**
```json
{
    "id": 1,
    "name": "Kamil",
    "surname": "Test",
    "email": "test@test.com"
}
```
---
`DELETE` `/api/v1/users/{userId}` -> **Deletes user with specified id**

**Example response**
```json
true
```
---
`PATCH` `/api/v1/users/{userId}` -> **Update user with new request body**

**Request body**
```json
{
"name": "new_name",
"surname": "new_surname",
"email": "new_email"
}
```
---
`GET` `/api/v1/users/rentals/{userId}` -> **Returns user rentals list**

**Example Response**
```json
[
    {
        "id": 1,
        "ownerId": 1,
        "vehicleId": 1,
        "periodStart": "2023-05-10T07:26:51.000+00:00",
        "periodEnd": "2024-05-10T07:26:45.000+00:00",
        "state": false
    }
]
```
## Vehicle
`GET` `/api/v1/vehicles` -> **Returns list of all vehicles**

**Example Response**
```json
[
    {
        "id": 1,
        "make": "Ford",
        "model": "Mustang",
        "year": 2018,
        "plate": "GWE 1234",
        "category": "Muscle",
        "rented": false
    }
]
```
---
`GET` `/api/v1/vehicles/{vehicleId}` -> **Returns vehicle object with specified id**

**Example Response**

```json
{
    "id": 1,
    "make": "Ford",
    "model": "Mustang",
    "year": 2018,
    "plate": "GWE 1234",
    "category": "Muscle",
    "rented": true
}
```
---
`POST` `/api/v1/vehicles` -> **Creates vehicle object and returns this object**

**Request Body**
```json
{
    "make": "make",
    "model": "model",
    "year": 2018,
    "plate": "plate",
    "category": "category_name"
}
```

**Example response**
```json
{
    "id": 1,
    "make": "Ford",
    "model": "Mustang",
    "year": 2018,
    "plate": "GWE 1234",
    "category": "Muscle",
    "rented": false
}
```

---
`PATCH` `/api/v1/vehicles/{vehicleId}` -> **Updates vehicle object with new request body and returns new object**

**Requst body**
```json
{
    "make": "new_make",
    "model": "new_model",
    "year": 2018,
    "plate": "new_plate",
    "category": "new_category"
}
```
**Example response**
```json
{
    "id": 1,
    "make": "Ford",
    "model": "Mustang",
    "year": 2018,
    "plate": "GWE 4321",
    "category": "Muscle",
    "rented": false
}
```
---
`PATCH` `/api/v1/vehicles/{vehicleId}/{state}` -> **Updates car with specified id with state `1 = true` `0 = false`**

**Example response**
```json
true
```
---
`DELETE` `/api/v1/vehicles/{vehicleId}` -> **Deletes car with specified id**

**Example response**
```json
true
```
---
## Rental
`GET` `/api/v1/rent` -> **Returns list with all rents objects**

**Example response**
```json
[
    {
        "id": 1,
        "ownerId": 1,
        "vehicleId": 3,
        "periodStart": "2023-05-10T07:26:51.000+00:00",
        "periodEnd": "2024-05-10T07:26:45.000+00:00",
        "state": false
    }
]
```
--- 
`POST` `api/v1/rent` -> **Creates new rent object with request body**

**Request Body Example**

```json
{
    "ownerId": 1,
    "vehicleId": 1,
    "timestampEnd": 1715326005000,
    "timestampStart": 1683703611000
}
```
---
`DELETE` `/api/v1/rent/{rentalId}` -> **Deletes rent with specified id**

**Example response**
```json
true
```

---
`PATCH` `/api/v1/rent/{rentalId}` -> **Updates rental object with new request body**

**Request Body Example**
```json
{
    "ownerId": 1,
    "vehicleId": 1,
    "timestampEnd": 1715327005000,
    "timestampStart": 1683703611000
}
```

**Example response**
```json
{
    "id": 1,
    "ownerId": 1,
    "vehicleId": 1,
    "periodStart": "2023-05-10T07:26:51.000+00:00",
    "periodEnd": "2024-05-10T07:43:25.000+00:00",
    "state": false
}
```


---
`PATCH` `/api/v1/rent/activate/{rentalId}` -> **Actvates rental**
**Response example**
```json
true
```
---
## Worker (Pracownik)


`GET` `/api/v1/workers` -> **Returns workers List**


---

`GET` `/api/v1/workers/{workerId}` -> **Returns worker object with specified id**

---

`POST` `/api/v1/workers` -> **Creates new worker object with same request body as user**

---

`DELETE` `/api/v1/workers/{workerId}` -> **Delete worker with specified id**

---

`PATCH` `/api/v1/workers/{workerId}` -> **Updates worker Data with new body**

---

## Worker Logs (Logi pracownika)
### !!! Log for renting is added automatically when worker creates rent !!!

`GET` `/api/v1/workers/logs/{workerId}` -> **Get logs for worker**

---

`DELETE` `/api/v1/workers/logs/{logId}` -> **Delete log with speicfied id**

---

`POST` `/api/v1/workers/logs` -> **Create log with request body**

**Request Body**
```json
{
  "workerId": 1,
  "actionDescription": "TestDescription"
}
```

