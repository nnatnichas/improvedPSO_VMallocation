package org.cloudbus.cloudsim.allocationpolicies;

import org.cloudbus.cloudsim.hosts.Host;

public class Velocity {
	private Host prevHost;
	private Host nextHost;
	
	public Velocity (Host prevHost, Host nextHost) {
		this.prevHost=prevHost;
		this.nextHost=nextHost;
	}
	
	public Host getPrevHost() {
		return this.prevHost;
	}
	
	public Host getNextHost() {
		return this.nextHost ;
	}
	
	public void setVelocity(Host prevHost, Host nextHost) {
		this.prevHost=prevHost;
		this.nextHost=nextHost;
	}
}
