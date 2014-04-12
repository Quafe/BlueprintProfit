-- derived from invBlueprintTypes table

--### Ignored because bullshit ###
--# - parentBlueprintTypeID
--# - researchTechTime

SELECT
	-- blueprint ID
	bp.blueprintTypeID,
	-- blueprint name
	blueprintID.typeName,
	-- parentBlueprintTypeID is bullshit
	--bp.parentBlueprintTypeID,
	-- product ID
	bp.productTypeID,
	-- product name
	productID.typeName,
	-- production time seconds
	bp.productionTime,
	-- units produced per run
	productID.portionSize,
	-- blueprint tech level
	bp.techLevel,
	-- research times for PE/ME
	bp.researchProductivityTime,
	bp.researchMaterialTime,
	-- base copy time. equal to time needed for half maxProductionLimit runs
	bp.researchCopyTime,
	-- not possible to research tech level
	--bp.researchTechTime,
	-- used for manufacturing time (time+(1-PE)*modifier)
	bp.productivityModifier,
	-- unsure if used at all (base mineral modifier?)
	bp.materialModifier,
	-- wastage factor for materials
	bp.wasteFactor,
	-- max runs per BPC
	bp.maxProductionLimit

FROM
	ebs_DATADUMP.dbo.invBlueprintTypes AS bp
	INNER JOIN ebs_DATADUMP.dbo.invTypes AS blueprintID ON bp.blueprintTypeID = blueprintID.typeID
	INNER JOIN ebs_DATADUMP.dbo.invTypes AS productID ON bp.productTypeID = productID.typeID

-- show only published blueprints
WHERE
	blueprintID.published = 1
	-- Echelon
	AND bp.productTypeID != 2863
	-- Primae
	AND bp.productTypeID != 3532
	-- Gnosis (should be 1 Trit now?!)
	AND bp.productTypeID != 3756
	-- Apotheosis
	AND bp.productTypeID != 29266
	-- 2771 published
	-- 318 not

	-- 1795 tech 1
	-- 881 tech 2
	-- 95 tech 3

