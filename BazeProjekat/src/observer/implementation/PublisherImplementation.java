package observer.implementation;

import java.util.ArrayList;
import java.util.List;

import observer.Notification;
import observer.Publisher;
import observer.Subscriber;

public class PublisherImplementation implements Publisher {

	private List<Subscriber> subscribers;
	
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	@Override
	public void AddSubscriber(Subscriber subscriber) {
		if(subscriber == null) {
			return;
		}
		if(this.subscribers == null) {
			this.subscribers = new ArrayList<Subscriber>();
		}
		if(this.subscribers.contains(subscriber)) {
			return;
		}
		this.subscribers.add(subscriber);
	}

	@Override
	public void RemoveSubscriber(Subscriber subscriber) {
		if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;
        this.subscribers.remove(subscriber);
	}

	@Override
	public void NotifySubscribers(Notification notification) {
		
		if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
		
		for(Subscriber subscriber : this.subscribers) {
			subscriber.Update(notification);
		}
	}
}
