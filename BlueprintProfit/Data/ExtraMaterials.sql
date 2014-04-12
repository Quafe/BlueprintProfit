SELECT
	iBT.productTypeID,
	iT.typeID,
	iT.typeName,
	rTR.quantity,
	rTR.damagePerJob
	
FROM
	ebs_DATADUMP.dbo.ramTypeRequirements rTR,
	ebs_DATADUMP.dbo.invTypes iT,
	ebs_DATADUMP.dbo.invBlueprintTypes iBT,
	ebs_DATADUMP.dbo.invGroups iG

WHERE
	rTR.requiredTypeID = iT.typeID
	AND rTR.typeID = iBT.blueprintTypeID
	-- 1 is manufacturing (see ram Activities)
	AND rTR.activityID = 1
	-- 16 are skills
	AND iG.categoryID != 16
	AND iT.groupID = iG.groupID
	AND iT.published = 1
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
			AND iBT.productTypeID = bp.productTypeID
	)

ORDER BY iBT.productTypeID
