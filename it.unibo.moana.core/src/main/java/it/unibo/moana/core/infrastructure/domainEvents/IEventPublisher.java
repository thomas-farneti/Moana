package it.unibo.moana.core.infrastructure.domainEvents;

import it.unibo.moana.messages.IEvent;

public interface IEventPublisher
{
    <T extends IEvent> void Publish(T event);// where T : Event;
}