Title: Electromagnetic-Thermal Coupled Simulation of a Thermomagnetic Release 
Mechanism

Author: Iorga Alexandru

Date: 10.10.2026

Abstract

The work aims to investigate a thermomagnetic release mechanism using a 2D 
axisymmetric model in COMSOL. The system is composed of an iron component, a 
permanent magnet and a copper coil. The main goal is to analyze the holding force 
against gravity as a electrical current varies and to find the point at which the iron’s 
temperature rises above the Curie Temperature.

1.Introduction
Thermomagnetic devices use the temperature-dependent magnetic properties of 
materials to operate mechanisms without the need for direct connections. When a 
ferromagnetic material reaches its Curie Temperature, its relative permeability drops 
and the material becomes paramagnetic. I made a model which calculates the 
electromagnetic hold force as a function of the current in the coil and used a Time
Dependent Study in order to determine the moment when the gravitational force 
exceeds the magnetic attraction.

2.Model Description

2.1 Geometry
The model is set up in a 2D axisymmetric space for computational efficiency. The 
geometry has 4 main regions: An iron piece at the base. A magnet place above the iron 
piece. A copper coil surrounding the upper part. A large air domain.

2.2 Materials
Air and copper. Custom cool material is a user-defined material with the properties the 
professor told us it should have. The analytical function describes the Curie transition.

2.3 Physics and Coupling
The simulation uses two coupled physics interfaces:
Magnetic Fields(mf)-Used for the magnetic flux, density and the electromagnetic force 
acting on the iron piece. Ampere’s Law to consider the custom permeability.
Heat Transfer in Solids and Fluids(ht)- Used for the temperature distribution caused by 
the heat flux, internal conduction and surface radiation

3.Boundary Condition and Parameters
A constant heat rate (P =20W) is applied to bottom of the iron piece. Surface radiation 
to the ambient and convective heat flux are applied to the outer boundaries of the 
magnet and the iron piece.
Instead of modeling a computationally expensive 10 µm air gap, a Thin Permeability 
Gap boundary condition is applied at the space between the magnet, the coil and the 
iron piece.
For parameters I have used a stationary Sweep in which I varied the coil current from 0 
to 4A in steps of 0.25.
For Time-Dependent the current is fixed at 3A, and the time varies from 0 to 400s

4. Meshing
At first a normal mesh was used, but because of the non-linearity of the custom iron’s 
permeability function near the 700K Curie point, I had to change it to an extra Fine. 
This change greatly increased the degrees of freedom which made possible the 
computation of the sharp changes in material properties during the thermal transition.

5. Results
With a constant 3A current, the thermal analysis shows a large temperature gradient 
starting at the base. By the end of the time-dependent study, the iron piece exceeded 
the 700K threshold, while the coil remained at a temperature of 293.15K
The stationary parametric sweep shows that the electromagnetic force increases 
exponentially as the current rises. The plot shows that the magnetic force reaches the 
threshold(15.1mN) at a minimum current of 2.1 A.
During the time-dependent study at 3A, the initial magnetic force is about 24mN. As 
the temperature increases and approaches the Curie point, the force drops suddenly. 
The net force reaches 0 mN at 315 to 320 seconds, this being the moment when the iron 
piece falls.

6. Errors
The step function s(T) offers a somewhat idealized version of the Curie Temperature. In 
reality the transition might have a different slope depending on the material of the iron 
piece.

7. Discussion
The simulation successfully illustrates the working principle of a thermomagnetic 
release. The continuous 20W base heating serves as a deliberate failure trigger. The 
Multiphysics coupling accurately captures the chain reaction: thermal energy, which 
determines the material properties, which in turn determines the strength of 
electromagnetic field and the mechanical force.

8. Personal Conclusions
This project gave me several important insights into FEM Modelling: 
Dealing with the 10 µm air gap: If I would try to draw a rectangle with a 10 µm height 
would’ve forced the mesh to create thousands of skewed elements, which would tricked 
my computation. Instead, I used a Thin Low Permeability Gap boundary condition to 
make this work.
The power of Multiphysics Coupling: I have seen the true capability of COMSOL with the 
use of the Heat Transfer module (Temperature T) act as a real-time dynamic variable 
in the magnetic Field material properties(µr)

<img width="863" height="469" alt="FINAL15first" src="https://github.com/user-attachments/assets/839eb220-f48b-4678-9838-b153411e7045" />
<img width="863" height="467" alt="FINAL15third" src="https://github.com/user-attachments/assets/aab49178-95ea-4e2b-9704-0b98220d3340" />
<img width="863" height="469" alt="FINAL15second" src="https://github.com/user-attachments/assets/bf2f5ed2-1ee5-461b-babe-15290d3c1199" />
