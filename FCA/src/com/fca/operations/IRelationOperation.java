package com.fca.operations;

public interface IRelationOperation {
	public Relation union(Relation IRelation);

	public Relation intersection(Relation IRelation);

	public Relation implication(Relation IRelation);

	public Relation composition(Relation IRelation);

	public Relation rightResidue(Relation IRelation);

	public Relation leftResidue(Relation IRelation);

	public Relation symetricQuotient(Relation IRelation);

	public Relation transpose();

	public Relation down();

	public Relation up();

	public Relation getEquivalence();

}
