package org.cloudbus.cloudsim.allocationpolicies;

import java.util.*;

import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;

public class Swarm {
	private Particle[] particles;
	//private double gBest; //**No gbest anymore.. need to find lbest from archive every velocity update method in Particle Swarm Optimization class
	//private Host[] gBestParticle;
	//private final int populationSize =40;
	private ArrayList<Archive> archive;
	private int archiveSize =10;
	private int countArchiveParticleId;
	//private Host[] hosts;
	
	public Swarm(Particle[] particles) { // set gBest
		this.particles= particles;
		archive = new ArrayList<Archive>(); //****For example, archive is 10 .. need to adjust again
		this.countArchiveParticleId =0;
		double[] mainFitness = new double[3];
		double[] tempFitness = new double[3];
		int count=0;
		while(count<this.particles.length) {
			mainFitness=this.particles[count].getFitness();
			System.out.print("\nparticle id: "+this.particles[count].getId()+": "+mainFitness[0]+", "+mainFitness[1]+", "+ mainFitness[2]);
			
			for(int i=1; i< this.particles.length;i++) {
				if(count!=i) {
					tempFitness= this.particles[i].getFitness(); // need to be (min,max,min)
					if(((tempFitness[0]<mainFitness[0]) && !(tempFitness[1]<mainFitness[1]) && !(tempFitness[2]>mainFitness[2])) ||
					(!(tempFitness[0]>mainFitness[0]) && (tempFitness[1]>mainFitness[1]) && !(tempFitness[2]>mainFitness[2])) ||
					(!(tempFitness[0]>mainFitness[0]) && !(tempFitness[1]<mainFitness[1]) && (tempFitness[2]<mainFitness[2]))
					){
						System.out.print(" | got dominated."); // not check anymore
						break;
					}	
				}
				//System.out.print("\n Initiate SwarmParticle, particle id"+this.particles[count].getId()+": ");
				if(i==particles.length-1) {
					this.addArchive(this.particles[count]);
				}
			}
			count++;
		}
		System.out.print("\nArchive size: "+this.archive.size()+" | Archive id: ");
		for(int i=0; i<this.archive.size();i++) {
			System.out.print(this.archive.get(i).getId()+" ");
		}
		System.out.print("|");
		
	}
	
	public int getCountOverallArchive() {
		return this.countArchiveParticleId;
	}
	
	public Particle[] getParticleSwarm () {
		return this.particles;
	}
	
	public ArrayList<Archive> getArchive(){
		return this.archive;
	}
	
	public Host[] getLBest(Particle p) {
		if(this.archive.size()==1) {
			return this.archive.get(0).getPosition();
		}
		int tempIndex =-1;
		double srd=-1;
		double tempSrd;
		double[] pFitness = p.getFitness();
		double[] archiveFitness =new double[3];
		for(int i=0; i<this.archive.size();i++) {
			archiveFitness =this.archive.get(i).getFitness();
			tempSrd = Math.sqrt(Math.abs(archiveFitness[0]-pFitness[0])) + Math.sqrt(Math.abs(archiveFitness[1]-pFitness[1])) + Math.sqrt(Math.abs(archiveFitness[2]-pFitness[2]));
			if(i==0) {
				srd=tempSrd;
				tempIndex = i;
			}else {
				if(tempSrd<srd) {
					srd=tempSrd;
					tempIndex=i;
				}
			}
		}
		return this.archive.get(tempIndex).getPosition();
	}
	
	public void addArchive (Particle p) {
		/*Host[] position = p1.getPosition();
		Velocity[] velo= p1.getVelocity();
		Particle p= new Particle(p1.getVm(),p1.getHost(),position,velo,100+this.countArchiveParticleId);*/
		//Particle p= new Particle(p1,100+this.countArchiveParticleId);
		Archive a = new Archive(p.getPosition(),p.getFitness(),this.countArchiveParticleId);
		this.countArchiveParticleId++;
		if(this.archive.size()==this.archiveSize) {
			this.removeArchive();
		}
		double[] pFitness = p.getFitness();
		double[] archiveFitness =new double[3];
		if(this.archive.size()==0) {
			this.archive.add(a);
		}else {
			for(int i =0; i<this.archive.size();i++) {
				archiveFitness =this.archive.get(i).getFitness();
				if(pFitness[0]<=archiveFitness[0]) {
					this.archive.add(i, a);
					System.out.print("addmiddle");
					break;
				}
				if(i==this.archive.size()-1) {
					this.archive.add(a);
					System.out.print("addEnd");
					break;
				}
			}
		}
	}
	
	public void removeArchive() {
		double neighborFactor=-1;
		double temp;
		int tempIndex=-1;
		double[] previous =new double[3];
		double[] current =new double[3];
		double[] next =new double[3];
		for(int i =1; i<this.archive.size()-2;i++) {
			if(i==1) {
				previous = this.archive.get(i-1).getFitness();
				current = this.archive.get(i).getFitness();
				next = this.archive.get(i+1).getFitness();
				neighborFactor = (Math.sqrt(Math.abs(previous[0]-current[0])) + Math.sqrt(Math.abs(previous[1]-current[1])) + Math.sqrt(Math.abs(previous[2]-current[2]))) 
						+ (Math.sqrt(Math.abs(next[0]-current[0])) + Math.sqrt(Math.abs(next[1]-current[1])) + Math.sqrt(Math.abs(next[2]-current[2]))) ;
				tempIndex=i;
			}else {
				previous=current;
				current=next;
				next= this.archive.get(i+1).getFitness();
			}
			temp = (Math.sqrt(Math.abs(previous[0]-current[0])) + Math.sqrt(Math.abs(previous[1]-current[1])) + Math.sqrt(Math.abs(previous[2]-current[2]))) 
					+ (Math.sqrt(Math.abs(next[0]-current[0])) + Math.sqrt(Math.abs(next[1]-current[1])) + Math.sqrt(Math.abs(next[2]-current[2]))) ;
			if(temp<neighborFactor) {
				tempIndex=i;
			}
		}
		this.archive.remove(tempIndex);
	}
	
	public void removeArchive(int index) {
		this.archive.remove(index);
	}
	
	
	
	
	
}
