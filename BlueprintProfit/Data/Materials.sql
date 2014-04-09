-- BASE MATERIALS
SELECT
	Query.productTypeID,
	--iTypes.typeName,
	Query.materialTypeID,
	Query.typeName,
	Query.quantity

FROM
	(
	SELECT
		productTypeID,
		materialTypeID,
		typeName,
		sum(quantity) quantity
	FROM
		(
			SELECT
				iTM.typeID productTypeID,
				iT.typeID materialTypeID,
				iT.typeName,
				quantity
			FROM
				ebs_DATADUMP.dbo.invTypes iT,
				ebs_DATADUMP.dbo.invTypeMaterials iTM
			WHERE
				iTM.materialTypeID = iT.typeID
				--AND invTypeMaterials.typeID = 2456
		UNION
			SELECT
				bt.productTypeID,
				invTypes.typeID materialTypeID,
				invTypes.typeName,
				invTypeMaterials.quantity * r.quantity * -1 quantity
			FROM
				ebs_DATADUMP.dbo.invTypes,
				ebs_DATADUMP.dbo.invTypeMaterials,
				ebs_DATADUMP.dbo.ramTypeRequirements r,
				ebs_DATADUMP.dbo.invBlueprintTypes bt
			WHERE
				invTypeMaterials.materialTypeID = invTypes.typeID
				AND invTypeMaterials.typeID = r.requiredTypeID
				AND r.typeID = bt.blueprintTypeID
				AND r.activityID = 1
				--AND bt.productTypeID = 2456
				AND r.recycle = 1
		) t
	GROUP BY
		productTypeID,
		materialTypeID,
		typeName
	) Query,
	ebs_DATADUMP.dbo.invTypes iTypes,
	ebs_DATADUMP.dbo.invGroups iGroups

WHERE
	Query.productTypeID = iTypes.typeID
	AND Query.quantity > 0
	AND iGroups.groupID = iTypes.groupID
	-- no Asteroid Refining
	AND iGroups.categoryID != 25
	-- no Stations
	--AND iGroups.categoryID != 3
	--AND iGroups.categoryID = 22
	-- no Credits (4096 Trit, 1024 Pyer, 256 Mex, 64 Iso, 16 Noc, 4 Zyd, 1 Meg)
	--AND Query.productTypeID != 29
	-- no Tournament Bubble TEST
	--AND Query.productTypeID != 26849

	-- only if there 'exists' a blueprint
	AND EXISTS
	(
		SELECT
			*
		FROM
			ebs_DATADUMP.dbo.invBlueprintTypes AS bp
			INNER JOIN ebs_DATADUMP.dbo.invTypes AS blueprintID ON bp.blueprintTypeID = blueprintID.typeID
			INNER JOIN ebs_DATADUMP.dbo.invTypes AS productID ON bp.productTypeID = productID.typeID
		WHERE
			blueprintID.published = 1
			AND Query.productTypeID = bp.productTypeID
	)

ORDER BY productTypeID
