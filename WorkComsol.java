/*
 * WorkComsol.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Oct 20 2026, 20:20 by COMSOL 6.4.0.429. */
public class WorkComsol {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("P:\\Facultate\\UTU\\FEM\\Lucrari");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").create("stat", "Stationary");
    model.study().remove("std1");
    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");

    model.param().set("Rp", "5[mm]");
    model.param().set("tp", "2.5[mm]");
    model.param().set("g", "10[um]");
    model.param().set("Rc", "5[mm]");
    model.param().set("Lc", "25[mm]");
    model.param().set("rci", "6[mm]");
    model.param().set("rco", "11[mm]");
    model.param().set("hco", "12[mm]");
    model.param().set("Rair", "60[mm]");
    model.param().set("Hair", "80[mm]");
    model.param().set("Pheat", "20[W]");
    model.param().set("Tinf", "293.15[K]");
    model.param().set("hconv", "10[W/(K*m^2)]");
    model.param().set("eps0", "0.8");
    model.param().set("IC", "3[A]");
    model.param().set("NC", "100");
    model.param().set("Tc", "700[K]");
    model.param().set("dT", "10[K]");
    model.param().set("mup0", "5000");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Very cool Material");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"mur_iron"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7870"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"450"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"80"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"1E7"});

    model.func().create("an1", "Analytic");
    model.func("an1").label("s");
    model.func("an1").set("funcname", "s");
    model.func("an1").label("Analtytic fc");
    model.func("an1").set("expr", "1/(1+exp((T-Tc)/dT))");
    model.func("an1").set("args", "T");
    model.func("an1").setIndex("plotargs", 2000, 0, 2);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rp", "tp"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Rc", "Lc"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "tp"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"rco-rci", "hco"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"rci", "tp+Lc-hco"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("Air");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Rair", "Hair"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "-20[mm]"});
    model.component("comp1").geom("geom1").run("r4");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("mur_iron", "1+(mup0-1)*s(T)");

    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", "", 0);
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("argunit", "K", 0);

    model.component("comp1").material("mat1").selection().set(2, 3);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat3").selection().set(4);

    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Litz");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "IC[A]");
    model.component("comp1").physics("mf").feature("coil1").set("N", "NC");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "IC");
    model.component("comp1").physics("mf").create("tg1", "ThinLowPermeabilityGap", 1);
    model.component("comp1").physics("mf").feature("tg1").selection().set(6, 12);
    model.component("comp1").physics("mf").feature("tg1").set("mur_mat", "userdef");
    model.component("comp1").physics("mf").feature("tg1").set("ds", "10[um]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").selection().set();
    model.component("comp1").physics("ht").feature("hf1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("ht").feature("hf1").setIndex("materialType", "nonSolid", 0);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "HeatRate");
    model.component("comp1").physics("ht").feature("hf1").set("P0", "Pheat");
    model.component("comp1").physics("ht").selection().set(2, 3, 4);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 1);
    model.component("comp1").physics("ht").feature("sar1").selection().set(8, 10, 11, 12, 13, 14, 15);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "Tinf");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", "eps0");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "hconv");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "Tinf");
    model.component("comp1").physics("ht").feature("hf2").selection().set(8, 10, 11);
    model.component("comp1").physics("ht").feature("sar1").selection().set(8, 10, 11);
    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mf").feature("fcal1").selection().set(2);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Rp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Rp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "IC", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,0.25,4)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").set("smooth", "internal");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("Magnetic Flux Density (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "mf.normB");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("evaluationsettings", "parent");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udensity", 2);
    model.result("pg2").feature("str1").set("color", "custom");
    model.result("pg2").feature("str1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("str1").set("evaluationsettings", "parent");
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("evaluationsettings", "parent");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Magnetic Flux Density, Revolved Geometry (mf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "mf.normB");
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg3").feature("vol1").set("evaluationsettings", "parent");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("solutionparams", "parent");
    model.result("pg3").feature("con1").set("expr", "mf.Psi");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("number", 10);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("color", "custom");
    model.result("pg3").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg3").feature("con1").set("evaluationsettings", "parent");
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").feature("con1").set("inheritplot", "vol1");
    model.result("pg3").feature("con1").feature().create("filt1", "Filter");
    model.result("pg3").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(2, 3);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.label("Final 15.mph");

    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mf.Forcez_1"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"Electromagnetic force, z-component"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg4").feature("glob1").setIndex("unit", "mN", 0);
    model.result("pg4").feature("glob1").set("xdata", "solution");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "mf.Forcez_1*1000", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("legendmethod", "automatic");
    model.result("pg4").feature("glob1").setIndex("expr", "mf.Forcez_1", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("expr", "mf.Forcez_1*1000", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Electromagnetic force, z-component(mN)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Magnetic force in function of the curent(0...4A)");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,1,400)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("Temperature (ht) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 401, 0);
    model.result("pg5").set("smooth", "internal");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("Magnetic Flux Density (mf) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 401, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "mf.normB");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg6").feature("surf1").set("evaluationsettings", "parent");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg6").feature("str1").set("titletype", "none");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udensity", 2);
    model.result("pg6").feature("str1").set("color", "custom");
    model.result("pg6").feature("str1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg6").feature("str1").set("evaluationsettings", "parent");
    model.result("pg6").feature("str1").set("maxlen", 0.4);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("inheritcolor", false);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("str1").set("data", "parent");
    model.result("pg6").feature("str1").selection().geom("geom1", 1);
    model.result("pg6").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").set("solutionparams", "parent");
    model.result("pg6").feature("con1").set("expr", "mf.Psi");
    model.result("pg6").feature("con1").set("titletype", "none");
    model.result("pg6").feature("con1").set("number", 10);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("coloring", "uniform");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result("pg6").feature("con1").set("color", "custom");
    model.result("pg6").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg6").feature("con1").set("evaluationsettings", "parent");
    model.result("pg6").feature("con1").set("inheritcolor", false);
    model.result("pg6").feature("con1").set("data", "parent");
    model.result("pg6").feature("con1").set("inheritplot", "surf1");
    model.result("pg6").feature("con1").feature().create("filt1", "Filter");
    model.result("pg6").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Magnetic Flux Density, Revolved Geometry (mf) 1");
    model.result("pg7").set("data", "rev2");
    model.result("pg7").setIndex("looplevel", 401, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("solutionparams", "parent");
    model.result("pg7").feature("vol1").set("expr", "mf.normB");
    model.result("pg7").feature("vol1").set("colortable", "Prism");
    model.result("pg7").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg7").feature("vol1").set("evaluationsettings", "parent");
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").set("solutionparams", "parent");
    model.result("pg7").feature("con1").set("expr", "mf.Psi");
    model.result("pg7").feature("con1").set("titletype", "none");
    model.result("pg7").feature("con1").set("number", 10);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("coloring", "uniform");
    model.result("pg7").feature("con1").set("colorlegend", false);
    model.result("pg7").feature("con1").set("color", "custom");
    model.result("pg7").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg7").feature("con1").set("evaluationsettings", "parent");
    model.result("pg7").feature("con1").set("inheritcolor", false);
    model.result("pg7").feature("con1").set("data", "parent");
    model.result("pg7").feature("con1").set("inheritplot", "vol1");
    model.result("pg7").feature("con1").feature().create("filt1", "Filter");
    model.result("pg7").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"mf.Forcez_1"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"Electromagnetic force, z-component"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg8").feature("glob1").setIndex("expr", "mf.Forcez_1 - 15.1[mN]", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "mf.Forcez_1 - 15.1", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "mN", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "mf.Forcez_1 - 15.1[mN]", 0);
    model.result("pg8").run();
    model.result().dataset("rev1").set("startangle", 0);
    model.result().dataset("rev1").set("revangle", 270);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").set("edgecolor", "black");
    model.result("pg9").run();
    model.result("pg9").set("data", "rev1");
    model.result("pg4").run();
    model.result("pg8").run();
    model.result("pg2").run();

    model.label("Final 15.mph");

    model.result("pg4").run();
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", false);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("titletype", "manual");
    model.result("pg8").feature("glob1").set("title", "Force Difference(mN)\n");
    model.result("pg8").run();

    model.label("Final 15.mph");

    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg9").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
