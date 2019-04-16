package org.cloudbus.cloudsim.allocationpolicies;

import org.cloudbus.cloudsim.hosts.Host;

public class Archive {

	private Host[] position;
	private double[] fitness = new double[3];
	private int id;
	
	public Archive(Host[] position, double[] fitness, int id) {
		this.position=position;
		this.fitness=fitness;
		this.id=id;
	}
	
	public Host[] getPosition() {
		return this.position;
	}
	
	public double[] getFitness() {
		return this.fitness;
	}
	
	public int getId() {
		return this.id;
	}
	
}
