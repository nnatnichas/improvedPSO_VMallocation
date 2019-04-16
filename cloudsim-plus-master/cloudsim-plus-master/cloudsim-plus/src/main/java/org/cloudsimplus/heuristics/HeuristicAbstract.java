/*
 * CloudSim Plus: A modern, highly-extensible and easier-to-use Framework for
 * Modeling and Simulation of Cloud Computing Infrastructures and Services.
 * http://cloudsimplus.org
 *
 *     Copyright (C) 2015-2016  Universidade da Beira Interior (UBI, Portugal) and
 *     the Instituto Federal de Educação Ciência e Tecnologia do Tocantins (IFTO, Brazil).
 *
 *     This file is part of CloudSim Plus.
 *
 *     CloudSim Plus is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     CloudSim Plus is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with CloudSim Plus. If not, see <http://www.gnu.org/licenses/>.
 */
package org.cloudsimplus.heuristics;

import org.cloudbus.cloudsim.distributions.ContinuousDistribution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.IntStream;

/**
 * A base class for {@link Heuristic} implementations.
 *
 * @author Manoel Campos da Silva Filho
 * @param <S> the class of solutions the heuristic will deal with, starting with a initial
 *           solution (usually random, depending on sub-classes implementations)
 *           and executing the solution search in order
 *           to achieve a satisfying solution (defined by a stop criteria)
 * @since CloudSim Plus 1.0
 */
public abstract class HeuristicAbstract<S extends HeuristicSolution<?>>  implements Heuristic<S> {
	/**
	 * Reference to the generic class that will be used to instantiate objects.
	 */
    private final Class<S> solutionClass;

	private final ContinuousDistribution random;
	/**
	 * @see #getNumberOfNeighborhoodSearchesByIteration()
	 */
    private int numberOfNeighborhoodSearchesByIteration;
	/**
	 * @see #getBestSolutionSoFar()
	 */
    private S bestSolutionSoFar;
	/**
	 * @see #getNeighborSolution()
	 */
    private S neighborSolution;

	/**
	 * @see #getSolveTime()
	 */
	private double solveTime;

	/**
	 * Creates a heuristic.
	 *
	 * @param random a random number generator
	 * @param solutionClass reference to the generic class that will be used to instantiate heuristic solutions
	 */
	HeuristicAbstract(ContinuousDistribution random, Class<S> solutionClass){
		this.solutionClass = solutionClass;
		this.random = random;
		this.numberOfNeighborhoodSearchesByIteration = 1;
		setBestSolutionSoFar(newSolutionInstance());
		setNeighborSolution(bestSolutionSoFar);
	}

	@Override
	public double getSolveTime() {
		return solveTime;
	}

	/**
	 * Sets the time taken to solve the heuristic.
	 * @param solveTime the time to set (in seconds)
	 */
	protected void setSolveTime(double solveTime) {
		this.solveTime = solveTime;
	}

	/**
	 *
	 * @return a random number generator
	 */
	protected ContinuousDistribution getRandom(){
		return random;
	}

	private S newSolutionInstance() {
	    try {
	        Constructor<S> c = solutionClass.getConstructor(Heuristic.class);
	        return c.newInstance(this);
	    } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException ex) {
	        throw new RuntimeException(ex);
	    }
	}

	/**
	 * Updates the state of the system in order to keep looking
	 * for a suboptimal solution.
	 */
	protected abstract void updateSystemState();

	@Override
	public int getRandomValue(int maxValue){
		final double uniform = getRandom().sample();

        /*always get an index between [0 and size[,
        regardless if the random number generator returns
        values between [0 and 1[ or >= 1*/
		return (int)(uniform >= 1 ? uniform % maxValue : uniform * maxValue);
	}

	@Override
	public S solve() {
		final long startTime = System.currentTimeMillis();
		setBestSolutionSoFar(getInitialSolution());
		while (!isToStopSearch()) {
			IntStream.range(0, getNumberOfNeighborhoodSearchesByIteration()).forEach(i -> {
				setNeighborSolution(createNeighbor(getBestSolutionSoFar()));
				if (getAcceptanceProbability() > getRandomValue(1)) {
					setBestSolutionSoFar(getNeighborSolution());
				}
			});

			updateSystemState();
		}
		setSolveTime((System.currentTimeMillis() - startTime)/1000.0);

		return getBestSolutionSoFar();
	}

	@Override
	public S getBestSolutionSoFar() {
	    return bestSolutionSoFar;
	}

	@Override
	public S getNeighborSolution() {
	    return neighborSolution;
	}

	/**
	 * Sets a solution as the current one.
	 * @param solution the solution to set as the current one.
	 */
	protected final void setBestSolutionSoFar(S solution) {
        this.bestSolutionSoFar = solution;
    }

	/**
	 * Sets a solution as the neighbor one.
	 * @param neighborSolution the solution to set as the neighbor one.
	 */
    protected final void setNeighborSolution(S neighborSolution) {
        this.neighborSolution = neighborSolution;
    }

	public int getNumberOfNeighborhoodSearchesByIteration() {
        return numberOfNeighborhoodSearchesByIteration;
    }

	public void setNumberOfNeighborhoodSearchesByIteration(int numberOfNeighborhoodSearches) {
        this.numberOfNeighborhoodSearchesByIteration = numberOfNeighborhoodSearches;
    }
}
