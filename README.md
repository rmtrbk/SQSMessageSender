# SQSMessageSender
Sends messages to `SQS` with using `Java` API

## Design
* `SQSMessengerServiceImpl` has the method that is capable of sending a message to `SQS` with `metadata`.

* `ClientBuilderManager` utility class build an `SQS` client to access `SQS` service APIs.

* `PropertyManager` reads required properties from the configuration file makes them available across the application.

## Configuring AWS Infrastructure
* Create an `IAM Group`.

* Create an `IAM User`.

* Add created `IAM User` to `IAM Group`'s `Amazon SQS` service.

* Go to `IAM Users`, select the created user and generate `key`/`value` pair in `Security credentials` tab.

* Create an `SQS` queue.

## How to test
This is a simple `Java` `Maven` project.

Fill in the values in the `properties` file.

Run `App` class.
