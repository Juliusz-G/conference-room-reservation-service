# Conference reservation room service
***
System collects conference room reservations for any organization registered.
## API Reference :round_pushpin:
***
#### Get model by ID

```http
  GET /conference-room/get/{conferenceRoomId}
```
```http
  GET /organisation/get/{organisationId}
```
```http
  GET /reservation/get/{reservationId}
```

| Parameter | Type   | Description  |
|:----------|:-------|:-------------|
| `...Id`   | `long` | **Required** |

#### Example
```http
http://localhost:8080/conference-room/get/1
```
#### JSON Result (if exists) :heavy_check_mark:
```json
{
  "id": 1,
  "name": "Test Name",
  "identifier": "3.60",
  "level": 5,
  "availability": true,
  "numberOfStandingPlaces": 100,
  "numberOfSittingPlaces": 200,
  "reservationList": null,
  "organisation": null
}
```
#### JSON Result (if doesn't exist) :x:
```json
{
  "timestamp": "2022-05-13T13:05:31.5718716",
  "message": "Conference room not found!",
  "httpCode": 404
}
```
***

## Tech Stack :man_technologist:
***
Spring Boot, H2, Swagger


## Authors :wave:
***
- [@muskLisek](https://github.com/muskLisek) :raising_hand_woman:
- [@Juliusz-G](https://github.com/Juliusz-G) :raising_hand_man:
- [@gracjanch](https://github.com/gracjanch) :raising_hand_man:
