package org.cloudbus.cloudsim.allocationpolicies;

import org.cloudbus.cloudsim.distributions.UniformDistr;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.power.PowerHost;
import org.cloudbus.cloudsim.hosts.power.PowerHostUtilizationHistory;
import org.cloudbus.cloudsim.power.models.PowerModel;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPower;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;
import org.cloudbus.cloudsim.vms.Vm;

public class Particle {
	private Vm[] vms;
	private Host[] pBest;
	private Velocity[] velocity;
	private Host[] position;
	private Host[] hosts;
	private double[] pBestFitness;
	private int id;
	
	public Particle(Vm[] vm, Host[] host, int id) {
		this.vms=vm;
		this.hosts=host;
		position = new Host[this.vms.length];
		velocity= new Velocity[this.position.length];
		double max= host.length*1.0;
		//UniformDistr rand = new UniformDistr(max);
		int tempHostId;
		for(int i=0 ; i<this.position.length; i++) {
			int random = (int)(Math.random()*host.length);
			//tempHostId = (int)(rand.sample());
			this.position[i] = host[random];
			random = (int)(Math.random()*host.length);
			//tempHostId = (int)(rand.sample());
			this.velocity[i] = new Velocity (this.position[i], host[random]);
		}
		
		this.pBest=this.position;
		this.pBestFitness=this.getFitness();
		this.id = id;
		
	}
	
	public Particle(Vm[] vm, Host[] host, Host[] position, Velocity[] velocity, int id) {
		this.vms=vm;
		this.hosts=host;
		this.position=position;
		this.velocity=velocity;
		this.pBest=this.position;
		this.pBestFitness=this.getFitness();
		this.id=id;
	}
	
	public Particle (Particle p, int id) {
		this.vms = p.vms;
		this.hosts = p.hosts;
		this.position=p.position;
		this.velocity=p.velocity;
		this.pBest=p.pBest;
		this.pBestFitness=p.pBestFitness;
		this.id=id;
	}
	public int getId() {
		return this.id;
		
	}
		
	public Vm[] getVm() {
		return this.vms;
	}
	
	public Host[] getHost() {
		return this.hosts;
	}
	public Host[] getPBest() {
		return this.pBest;
	}
	
	public double[] getPBestFitness() {
		return this.pBestFitness;
	}
	
	public void setPBest(Host[] pBest, double[] pBestFitness) {
		this.pBest=pBest;
		this.pBestFitness=pBestFitness;
	}
	
	public Velocity[] getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(Velocity[] newVelocity) {
		/*Velocity temp;
		for(int j=0; j<this.velocity.length; j++) {
			temp= this.velocity[j];
			this.velocity[j].setVelocity(temp.getNextHost(), newVelocity[j]);
		}*/
		this.velocity=newVelocity;
	}
	
	public Host[] getPosition() {
		return this.position;
	}
	
	public void setPosition (Host[] position) {
		for(int i=0;i<position.length;i++) {
			this.position[i]=position[i];
		}
	}
	
	public double[] getFitness() {
		double[] utilizationP = new double[hosts.length] ; // utilization after added this vm batch for each physical machine from this particle
		double[] utilizationBefore = new double[hosts.length] ;
		double avgUtilCpu =0.0;
		int count=0;
		//double countUtil =0.0;
		//System.out.print("\nUtilization of host: ");
		for (int j=0; j< hosts.length; j++) {
			utilizationP[j]=hosts[j].getUtilizationOfCpu();
			utilizationBefore[j]= utilizationP[j];
			for(int i=0; i< this.position.length; i++) {
				if(hosts[j].equals(this.position[i]) ){
					utilizationP[j]=utilizationP[j]+(this.vms[i].getMips()/hosts[j].getMips());
				}
			}
			if(utilizationP[j]>utilizationBefore[j]) {
				avgUtilCpu= avgUtilCpu+utilizationP[j];
				count++;
			}
			//System.out.print(utilizationP[j]+" ");
			//countUtil=countUtil+utilizationP[j];
		}
		//System.out.print("| ");//\ntotal utilization of this host: "+countUtil);
		
		if(count==0) {
			avgUtilCpu=0;
		}else {
			avgUtilCpu=avgUtilCpu/count;
		}
		double fitness= getEnergyFitness(utilizationP);
		double fitnessThroughput = avgUtilCpu;
		double fitnessPerf = this.getPerformanceFitness(utilizationP, avgUtilCpu);
		//System.out.print("before: "+estPowerU+"after: "+estPowerU1);
		double[] overallFitness = {fitness,fitnessThroughput,fitnessPerf};
		//System.out.print("\nEnergy fitness: "+fitness+" | Throughput fitness: "+fitnessThroughput+" | Performance fitness: "+fitnessPerf);
		return overallFitness;
	}
	
	private double getEnergyFitness(double[] utilizationP) {
		double fitness=0.0;
		double estPowerU=0.0;
		double estPowerU1=0.0;
		double estPowerTotal;
		int util1; // of utilization u
		int util2; // of utilization u
		double power1;
		double power2;
		double delta;
		//System.out.print("\nEnergy consumption of particle: ");
		for (int k=0; k<hosts.length;k++) {
			PowerHostUtilizationHistory p = (PowerHostUtilizationHistory)hosts[k];
			PowerModelSpecPower pm=(PowerModelSpecPower) p.getPowerModel();
			estPowerU= estPowerU+((PowerHostUtilizationHistory)hosts[k]).getPower();
			if (utilizationP[k] % 0.1 == 0) {
				estPowerU1 =pm.getPowerData((int) (utilizationP[k]* 10));
			}else {
				util1 = (int) Math.floor(utilizationP[k] * 10);
				util2 = (int) Math.ceil(utilizationP[k] * 10);
				power1 = pm.getPowerData(util1);
				power2 = pm.getPowerData(util2);
				delta = (power2 - power1) / 10;
				estPowerU1 =power1 + delta * (utilizationP[k] - (double) util1 / 10) * 100;
			}
			estPowerTotal = estPowerU1-estPowerU;
			fitness = fitness+estPowerTotal;
			
		}
		return fitness;
	}
	
	private double getPerformanceFitness (double[]utilizationCpu, double avgUtilCpu) { //degree of imbalance level
		double resrcImbl=0.0;
		for(int i=0; i<hosts.length;i++) {
			if(utilizationCpu[i]>0.0) {
				resrcImbl = resrcImbl+ Math.sqrt(Math.pow(utilizationCpu[i]-avgUtilCpu,2));
			}
		}		
		return resrcImbl;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
