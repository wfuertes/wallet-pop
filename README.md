# wallet-pop

### Required
```
$ docker run -d --hostname rabbitmq --name rabbitmq -p 15672:15672 rabbitmq:3-management
$ docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```