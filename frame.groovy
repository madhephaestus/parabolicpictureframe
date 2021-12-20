//Your code here
def frameWidth =4*25.4
def glassThickness = 6.5
def baseHeight = 10
def base = Parabola.coneByHeight(20, baseHeight)
			.rotx(90)
			.toZMin()

def supportConnectCone = Parabola.coneByHeight(glassThickness, baseHeight)
			.rotx(90)
			.toZMin()
def supportThickness = CSG.unionAll([
					supportConnectCone.movey(glassThickness/2),
				     supportConnectCone.movey(glassThickness/-2),
					supportConnectCone.movey(glassThickness/2).movex(frameWidth),
				     supportConnectCone.movey(glassThickness/-2).movex(frameWidth)])	
				     .hull()
return CSG.unionAll([
base,
base.movex(frameWidth),
supportThickness
])				   