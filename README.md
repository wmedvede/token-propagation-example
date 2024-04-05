# mta-bearer-token-propagation


## NO propagation

When the propagation is not set the WF will set it own token configured in the application.properties: MY-TOKEN

1. mvn clean install

2. start the external-service-mock and mta-bearer-token-propagation applications

3. create a wf instance and verify

curl -H 'Content-Type: application/json' -H 'Accept: application/json' -X POST -d '{}' http://localhost:8080/test-token-propagation

In the external-service-mock you should see something like this:

2024-04-05 11:22:06,009 DEBUG [org.acm.ExternalServiceResource] (executor-thread-1) getTrackers.httpHeaders: [Accept=application/json,Authorization=Bearer MY-TOKEN,Connection=Keep-Alive,Host=localhost:8282,kogitoprocid=test-token-propagation,kogitoprocinstanceid=21c03267-0fea-4500-8da0-454a12a39bf0,kogitoprocist=Active,kogitoproctype=SW,kogitoprocversion=1.0,User-Agent=Apache-HttpClient/4.5.14 (Java/17.0.9)]


## Bearer token propagation

When the propagation is SET, you must pass the token to propagate when the WF is started, otherwise there's no
token to propagate.

1. mvn clean install -Dquarkus.profile=propagation

2. start the external-service-mock and mta-bearer-token-propagation applications

3. create a wf instance and verify

Use a post like this:

curl -H 'Authorization: Bearer TOKEN_PASSED_TO_THE_WF_AND_TO_BE_PROPAGATED' -H 'Content-Type: application/json' -H 'Accept: application/json' -X POST -d '{}' http://localhost:8080/test-token-propagation

In the external-service-mock you should see something like this:

2024-04-05 11:25:01,808 DEBUG [org.acm.ExternalServiceResource] (executor-thread-1) getTrackers.httpHeaders: [Accept=application/json,Authorization=Bearer TOKEN_PASSED_TO_THE_WF_AND_TO_BE_PROPAGATED,Connection=Keep-Alive,Host=localhost:8282,kogitoprocid=test-token-propagation,kogitoprocinstanceid=f8646856-6037-4e7b-885d-158bf2583e75,kogitoprocist=Active,kogitoproctype=SW,kogitoprocversion=1.0,User-Agent=Apache-HttpClient/4.5.14 (Java/17.0.9)]

