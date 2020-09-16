package com.vladmihalcea.OneToMany

import jakarta.persistence.{Entity, FetchType, GeneratedValue, GenerationType, Id, ManyToOne, Table}

import scala.beans.BeanProperty

@Entity(name = "PostComment")
@Table(name = "post_comment", schema = "OLINGO")
class PostComment(@BeanProperty val review: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private val id = 0L

  @BeanProperty
  @ManyToOne(fetch = FetchType.LAZY)
  var post: Post = _

  // Default constructor for hibernate
  // No public visibility required
  private def this() = this(null)



}