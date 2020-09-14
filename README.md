# Decathlon points calculator
REST API to calculate decathlon points

### Usage
* **URL**

  `GET /api/v1/sport/:sport/result/:result`

*  **URL Params**

   **Required:**
 
   `sport: Decathlon event name`
   
   `result: Time for track events, Distance for field events`

### Example

    GET http://localhost:8080/api/v1/sport/100%20m/result/10.395
    
### UI URL

    http://localhost:8080/