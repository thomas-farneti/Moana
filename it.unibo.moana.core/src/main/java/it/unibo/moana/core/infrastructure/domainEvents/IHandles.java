package it.unibo.moana.core.infrastructure.domainEvents;

public interface IHandles<T>
{
    void Handle(T message);
}
