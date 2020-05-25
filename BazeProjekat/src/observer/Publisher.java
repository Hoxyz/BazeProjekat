package observer;

public interface Publisher {
	public void AddSubscriber(Subscriber subscriber);
	public void RemoveSubscriber(Subscriber subscriber);
	public void NotifySubscribers(Notification notification);
}
