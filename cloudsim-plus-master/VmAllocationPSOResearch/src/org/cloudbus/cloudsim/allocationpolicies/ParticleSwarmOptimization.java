package org.cloudbus.cloudsim.allocationpolicies;

import org.cloudbus.cloudsim.distributions.UniformDistr;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;

public class ParticleSwarmOptimization {
	
	private Swarm swarm;
	
		
	public ParticleSwarmOptimization(Swarm swarm) {
		this.swarm=swarm;
		
		
	}
	
	public Host[] start(int maxIter, double weight, double k1, double k2, double mutationRate) {
		int i=0;
		Velocity[] newV;
		Host[] newP;
		Velocity[] vTemp;
		Host[] pTemp;
		Host[] pBest;
		double[] pBestFitness;
		Host[] lBest;
		
		while(i<maxIter) {
			for(Particle p: swarm.getParticleSwarm()) {
				lBest = this.swarm.getLBest(p);
				double[] pFit = p.getFitness();
				System.out.print("\nIteration no: "+i+" | PSO id"+p.getId()+" (old), fitness: "+pFit[0]+", "+pFit[1]+", "+ pFit[2]);
				
				
				/*System.out.print(" | position: ");
				for(int j=0; j<p.getPosition().length;j++) {
					System.out.println(p.getPosition()[j]+" ");
				}
				System.out.println("|");*/
				
				newV = new Velocity[p.getVelocity().length];
				newP = new Host[p.getPosition().length];
				vTemp = p.getVelocity();
				pTemp= p.getPosition();
				pBest= p.getPBest();
				pBestFitness= p.getPBestFitness();
				for(int j=0; j<p.getPosition().length;j++) {
									
					newV[j]= additionOp(multiplicationOp(weight,vTemp[j]),	
							multiplicationOp((k1),subtractionOp(pBest[j],pTemp[j])));
							
					newV[j]= additionOp(newV[j],multiplicationOp((k2),subtractionOp(lBest[j],pTemp[j])));
					newP[j]= transitionOp(p.getPosition()[j],newV[j]);
					
				}
				
				
				
				p.setPosition(newP);
				p.setVelocity(newV);
				
				pFit = p.getFitness();
				System.out.print("\nPSO id"+p.getId()+" (new), fitness: "+pFit[0]+", "+pFit[1]+", "+ pFit[2]);
				System.out.print(" | Update pBest: ");
				if(((pFit[0]<pBestFitness[0]) && !(pFit[1]<pBestFitness[1]) && !(pFit[2]>pBestFitness[2])) ||
						(!(pFit[0]>pBestFitness[0]) && (pFit[1]>pBestFitness[1]) && !(pFit[2]>pBestFitness[2])) ||
						(!(pFit[0]>pBestFitness[0]) && !(pFit[1]<pBestFitness[1]) && (pFit[2]<pBestFitness[2]))
						){
					p.setPBest(newP,pFit);
					System.out.print("update");
				}else if(((pBestFitness[0]<pFit[0]) && !(pBestFitness[1]<pFit[1]) && !(pBestFitness[2]>pFit[2])) ||
						(!(pBestFitness[0]>pFit[0]) && (pBestFitness[1]>pFit[1]) && !(pBestFitness[2]>pFit[2])) ||
						(!(pBestFitness[0]>pFit[0]) && !(pBestFitness[1]<pFit[1]) && (pBestFitness[2]<pFit[2]))
						){
					//pBest is the same
					System.out.print("not update");
				}else {
					if((int)(Math.random()*1)==1) {
						p.setPBest(newP, pFit);
						System.out.print("update");
					}
					System.out.print("not update");
				}
				
				if(Math.random()*1<mutationRate) {
					//countMutation++;
					System.out.print("\nMutation!");
					newP=this.mutationOp(p);
					p.setPosition(newP);
					pFit = p.getFitness();
					System.out.print("| PSO id"+p.getId()+" (mutation), fitness: "+pFit[0]+", "+pFit[1]+", "+ pFit[2]);
					System.out.print(" | Update pBest: ");
					if(((pFit[0]<pBestFitness[0]) && !(pFit[1]<pBestFitness[1]) && !(pFit[2]>pBestFitness[2])) ||
							(!(pFit[0]>pBestFitness[0]) && (pFit[1]>pBestFitness[1]) && !(pFit[2]>pBestFitness[2])) ||
							(!(pFit[0]>pBestFitness[0]) && !(pFit[1]<pBestFitness[1]) && (pFit[2]<pBestFitness[2]))
							){
						p.setPBest(newP,pFit);
						System.out.print("update");
					}else if(((pBestFitness[0]<pFit[0]) && !(pBestFitness[1]<pFit[1]) && !(pBestFitness[2]>pFit[2])) ||
							(!(pBestFitness[0]>pFit[0]) && (pBestFitness[1]>pFit[1]) && !(pBestFitness[2]>pFit[2])) ||
							(!(pBestFitness[0]>pFit[0]) && !(pBestFitness[1]<pFit[1]) && (pBestFitness[2]<pFit[2]))
							){
						//pBest is the same
						System.out.print("not update");
					}else {
						if((int)(Math.random()*1)==1) {
							p.setPBest(newP, pFit);
							System.out.print("update");
						}
						System.out.print("not update");
					}
				}
				
				/*System.out.print("\nPSO id"+countId+" (new), fitness: ");
				System.out.print(" | position: ");
				for(int j=0; j<p.getPosition().length;j++) {
					System.out.println(newP[j]+" ");
				}
				System.out.println("|");*/
				
				double[] archiveFitness = new double[3];
				int currentSize=this.swarm.getArchive().size();
				System.out.print("\n Archive no: "+currentSize+" | update Archive: ");
				for(int k=0;k<currentSize;k++) {
					Archive archive =this.swarm.getArchive().get(k);
					archiveFitness = this.swarm.getArchive().get(k).getFitness();
					System.out.print("\nArchive id: "+k+" fitness: "+archiveFitness[0]+", "+archiveFitness[1]+", "+ archiveFitness[2]);
					/*if((archiveFitness[0]==pFit[0]) && (archiveFitness[1]==pFit[1]) && (archiveFitness[2]==pFit[2])) {
						System.out.print(" | archive and particle hv same fitness");
						break;
					}else if(((archiveFitness[0]<pFit[0]) && !(archiveFitness[1]<pFit[1]) && !(archiveFitness[2]>pFit[2])) ||
							(!(archiveFitness[0]>pFit[0]) && (archiveFitness[1]>pFit[1]) && !(archiveFitness[2]>pFit[2])) ||
							(!(archiveFitness[0]>pFit[0]) && !(archiveFitness[1]<pFit[1]) && (archiveFitness[2]<pFit[2]))
							){
						System.out.print(" | archive s fitness is better");
						break;
						
					}else if(((pFit[0]<archiveFitness[0]) && !(pFit[1]<archiveFitness[1]) && !(pFit[2]>archiveFitness[2])) ||
							(!(pFit[0]>archiveFitness[0]) && (pFit[1]>archiveFitness[1]) && !(pFit[2]>archiveFitness[2])) ||
							(!(pFit[0]>archiveFitness[0]) && !(pFit[1]<archiveFitness[1]) && (pFit[2]<archiveFitness[2]))
							){
						System.out.print(" | particle s fitness is better");
						this.swarm.removeArchive(k);
						System.out.print(" | current archive size: "+this.swarm.getArchive().size());
						currentSize--;
						
					}*/
					
					
					
					if(((pFit[0]<archiveFitness[0]) && !(pFit[1]<archiveFitness[1]) && !(pFit[2]>archiveFitness[2])) ||
							(!(pFit[0]>archiveFitness[0]) && (pFit[1]>archiveFitness[1]) && !(pFit[2]>archiveFitness[2])) ||
							(!(pFit[0]>archiveFitness[0]) && !(pFit[1]<archiveFitness[1]) && (pFit[2]<archiveFitness[2]))
							){
						System.out.print(" | particle s fitness is better");
						this.swarm.removeArchive(k);
						System.out.print(" | current archive size: "+this.swarm.getArchive().size());
						currentSize--;
						
					}else if(((archiveFitness[0]<pFit[0]) && !(archiveFitness[1]<pFit[1]) && !(archiveFitness[2]>pFit[2])) ||
							(!(archiveFitness[0]>pFit[0]) && (archiveFitness[1]>pFit[1]) && !(archiveFitness[2]>pFit[2])) ||
							(!(archiveFitness[0]>pFit[0]) && !(archiveFitness[1]<pFit[1]) && (archiveFitness[2]<pFit[2]))
							){
						System.out.print(" | archive s fitness is better");
						break;
						
					}else if((archiveFitness[0]==pFit[0]) && (archiveFitness[1]==pFit[1]) && (archiveFitness[2]==pFit[2])) {
						System.out.print(" | archive and particle hv same fitness");
						break;
					}
					
					boolean check =false;
					for(int m=0;m<newP.length;m++) {
						if(newP[m].getId()!=archive.getPosition()[m].getId()) {
							check=true;
							break;
						}
					}
					if(check==false) {
						System.out.print(" | break because particles the same");
						break;
					}
					
					if(k==currentSize-1) {
						System.out.print(" | Finally add!");
						//int newIndex = 100+this.swarm.getCountOverallArchive();
						//Particle p1= new Particle(p,newIndex);
						
						this.swarm.addArchive(p);
						
					}
					
				}
				System.out.print(" | finish update Archive | archive size: "+ this.swarm.getArchive().size()+" members: ");
				for(int m=0;m<this.swarm.getArchive().size();m++) {
					System.out.print(this.swarm.getArchive().get(m).getFitness()[0]+" ");
				}
				System.out.print(" | ");
			}	
			
			i++;
		}
		
		System.out.print("\nOptimization Results: ");
		for(int a=0; a<this.swarm.getArchive().size();a++) {
			System.out.print("\n Sol"+a+" : ");
			Host[] pos =this.swarm.getArchive().get(a).getPosition();
			for(int j =0 ; j<pos.length;j++) {
				System.out.print(pos[j].getId()+", ");
			}
			System.out.print("|\n");
			
		}
		return swarm.getArchive().get(0).getPosition();
		
	}
	
	public Swarm getSwarm() {
		return this.swarm;
	}
	
	
	
	public Host transitionOp (Host position, Velocity v) {
		if(position == v.getPrevHost()) {
			return v.getNextHost();
		}else {
			return position;
		}
	}
	
	public Velocity subtractionOp (Host p1, Host p2) {
		return new Velocity(p2,p1);
	}
	
	public Velocity additionOp (Velocity v1, Velocity v2) {
		if(v1.getNextHost().equals(v2.getPrevHost())) {
			return new Velocity(v1.getPrevHost(), v2.getNextHost());
		}else {
			return v1;
		}
	}
	
	public Velocity multiplicationOp (double c, Velocity v) {
		UniformDistr random = new UniformDistr (1);
		if(Math.random()*1<=c) {
			return new Velocity(v.getPrevHost(),v.getPrevHost());
		}else {
			return v;
		}
	}
	
	public Host[] mutationOp(Particle p) {
		// original particle
		
		Host[] newPosUniform = new Host[p.getPosition().length];
		Host[] pHost = p.getHost();
		int randIndex;
		for(int i=0; i<newPosUniform.length;i++) {
			randIndex = (int)(Math.random()*pHost.length);
			newPosUniform[i]= pHost[randIndex];
		}
		return newPosUniform;
	}
	
}
