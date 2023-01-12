# Project Plan Demo

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes

## Steps

* Perform build

    ```
    mvn clean install -DskipTests
    ```

* Run the application

    ```
    mvn spring-boot:run
    ```

## Project Plan Endpoints

   ```
      Description: Get all Existing Project Plan
      URL: http://localhost:8080/api/project_plan/all
      Method: Get
      Sample Return Payload
      {
          "statusCode": "OK",
          "data": [
              {
                  "id": 1,
                  "name": "Project Plan 1"
              },
              {
                  "id": 2,
                  "name": "Project Plan 2"
              },
              {
                  "id": 3,
                  "name": "Project Plan 3"
              },
              {
                  "id": 4,
                  "name": "Project Plan 4"
              },
              {
                  "id": 5,
                  "name": "Project Plan 5"
              }
          ]
      }
   ```

   ```
      Description: Get existing Project Plan by ID with computed average schedule for all task
      URL: http://localhost:8080/api/project_plan/{id}
      Method: Get
      Sample Return Payload
      {
          "httpStatus": "OK",
          "projectPlan": {
              "id": 1,
              "name": "Project Plan 1",
              "schedule": "17 Days"
          },
          "erroMessages": null
      }
   ```

   ```
      Description: Create new Project Plan
      URL: http://localhost:8080/api/project_plan/proj/{name}
      Method: Post
      Sample Return Payload
      {
          "httpStatus": "CREATED",
          "data": "Project Plan 'Project Plan (Accounting Module)' is was created successfully"
      }
   ```

## Task Endpoints

   ```
      Description: Get All Task by Project Plan Id
      URL: http://localhost:8080/api/task/all
      Method: Get
      Sample Return Payload
      {
        "httpStatus": "OK",
        "task": [
            {
                "id": 1,
                "name": "Design Project Plan 1",
                "status": "Complete",
                "start_date": "2023-01-11",
                "end_date": "2023-01-18",
                "dependency": []
            },
            {
                "id": 8,
                "name": "Testing (Module  1)",
                "status": "In Progress",
                "start_date": "2023-01-11",
                "end_date": "2023-01-18",
                "dependency": [
                    {
                        "id": 1,
                        "name": "Design Project Plan 1",
                        "status": "Complete",
                        "start_date": "2023-01-11",
                        "end_date": "2023-01-18",
                        "dependency": []
                    },
                    {
                        "id": 2,
                        "name": "Coding (Implem  1)",
                        "status": "Complete",
                        "start_date": "2023-01-01",
                        "end_date": "2023-01-11",
                        "dependency": []
                    },
                    {
                        "id": 3,
                        "name": "Coding (Implem  2)",
                        "status": "Complete",
                        "start_date": "2023-01-02",
                        "end_date": "2023-01-11",
                        "dependency": []
                    },
                    {
                        "id": 4,
                        "name": "Coding (Implem  3)",
                        "status": "In Progress",
                        "start_date": "2023-01-08",
                        "end_date": "2023-01-16",
                        "dependency": []
                    }
                ]
            },
            {
                "id": 10,
                "name": "Testing (Module  3)",
                "status": "In Progress",
                "start_date": "2023-01-11",
                "end_date": "2023-01-18",
                "dependency": [
                    {
                        "id": 7,
                        "name": "Coding (Implem  6)",
                        "status": "Complete",
                        "start_date": "2023-01-01",
                        "end_date": "2023-01-11",
                        "dependency": []
                    }
                ]
            }
        ],
        "erroMessages": null
    } 
   ```

   ```
      Description: Get All Task by Task id
      URL: http://localhost:8080/api/task/{id}
      Method: Get
      Sample Return Payload
      {
          "httpStatus": "OK",
          "task": {
              "id": 10,
              "name": "Testing (Module  3)",
              "status": "In Progress",
              "start_date": "2023-01-11",
              "end_date": "2023-01-18",
              "dependency": [
                  {
                      "id": 7,
                      "name": "Coding (Implem  6)",
                      "status": "Complete",
                      "start_date": "2023-01-01",
                      "end_date": "2023-01-11",
                      "dependency": []
                  }
              ]
          },
          "erroMessages": null
      }
   ```

   ```
      Description: Get All Task by Proj id
      URL: http://localhost:8080/api/task/proj/{id}
      Method: Get
      Sample Return Payload
      {
          "httpStatus": "OK",
          "task": {
              "id": 10,
              "name": "Testing (Module  3)",
              "status": "In Progress",
              "start_date": "2023-01-11",
              "end_date": "2023-01-18",
              "dependency": [
                  {
                      "id": 7,
                      "name": "Coding (Implem  6)",
                      "status": "Complete",
                      "start_date": "2023-01-01",
                      "end_date": "2023-01-11",
                      "dependency": []
                  }
              ]
          },
          "erroMessages": null
      }
   ```



   ```
      Description: Create Task by Proj id
      URL: http://localhost:8080/api/task/proj/{id}
      Method: Post
      Sample Request Payload
      {
        "name": "Coding (Accounting Module)",
        "task_dependency": "Coding (Implem  3),Coding (Implem  4),Coding (Implem  5)"
      }
      Sample Return Payload
      {
          "httpStatus": "OK",
          "task": {
              "id": 11,
              "name": "Coding (Accounting Module)",
              "status": "Not Started",
              "start_date": "2023-01-19",
              "end_date": "2023-01-26",
              "dependency": [
                  {
                      "id": 4,
                      "name": "Coding (Implem  3)",
                      "status": "In Progress",
                      "start_date": "2023-01-08",
                      "end_date": "2023-01-16",
                      "dependency": []
                  },
                  {
                      "id": 5,
                      "name": "Coding (Implem  4)",
                      "status": "In Progress",
                      "start_date": "2023-01-07",
                      "end_date": "2023-01-16",
                      "dependency": []
                  },
                  {
                      "id": 6,
                      "name": "Coding (Implem  5)",
                      "status": "In Progress",
                      "start_date": "2023-01-19",
                      "end_date": "2023-01-18",
                      "dependency": []
                  }
              ]
          },
          "erroMessages": []
      }
   ```

   ```
      Description: Update Task by Proj id
      URL: http://localhost:8080/api/task/proj/{id}
      Method: Post
      Sample Request Payload
      {
        "name": "Coding (Accounting Module)",
        "task_dependency": "Coding (Implem  3),Coding (Implem  4),Coding (Implem  5)"
      }
      Sample Return Payload
      {
          "httpStatus": "OK",
          "task": {
              "id": 11,
              "name": "Coding (Accounting Module)",
              "status": "Not Started",
              "start_date": "2023-01-19",
              "end_date": "2023-01-26",
              "dependency": [
                  {
                      "id": 4,
                      "name": "Coding (Implem  3)",
                      "status": "In Progress",
                      "start_date": "2023-01-08",
                      "end_date": "2023-01-16",
                      "dependency": []
                  },
                  {
                      "id": 5,
                      "name": "Coding (Implem  4)",
                      "status": "In Progress",
                      "start_date": "2023-01-07",
                      "end_date": "2023-01-16",
                      "dependency": []
                  },
                  {
                      "id": 6,
                      "name": "Coding (Implem  5)",
                      "status": "In Progress",
                      "start_date": "2023-01-19",
                      "end_date": "2023-01-18",
                      "dependency": []
                  }
              ]
          },
          "erroMessages": []
      }
   ```