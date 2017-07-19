package top.vanzhu.framework.eventing.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.vanzhu.framework.eventing.DomainEvent;
import top.vanzhu.framework.eventing.EventForwarder;

@Service
public class CloudEventForwarder implements EventForwarder {
    private @Autowired MessageChannel output;

    @Override
    public void forward(DomainEvent event) {
        output.send(createMessage(event));
    }

    @Override
    public void forward(DomainEvent event, String routingHint) {
        output.send(createMessage(event));
    }

    private Message<?> createMessage(DomainEvent event) {
        return MessageBuilder.withPayload(event.eventPayload())
                .setHeader("X-EVENT-NAME", event.eventName())
                .setHeader("X-EVENT-ID", event.eventId())
                .setHeader("event-name", event.eventName())
                .setHeader("event-type", event.eventName())
                .setHeader("message_id", event.eventId())
                .build();
    }
}
