package com.vladmihalcea.OneToMany

import jakarta.persistence.{CascadeType, Entity, FetchType, GeneratedValue, GenerationType, Id, OneToMany, Table}
import java.{util => ju}

import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.beans.BeanProperty

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = "post", schema = "OLINGO")
class Post(@BeanProperty val title: String) {
  @OneToMany(mappedBy = "post", cascade = Array(CascadeType.ALL), orphanRemoval = true)
  // Use Java collection types instead of Scala ones to make JPA happy
  final private val comments: ju.List[PostComment] = new ju.ArrayList[PostComment]

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id = 0L

  // Default constructor for hibernate
  // No public visibility required
  private def this() = this(null)

  //Constructors, getters and setters removed for brevity
  def addComment(comment: PostComment): Unit = {
    comments.add(comment)
    comment.setPost(this)
  }

  def removeComment(comment: PostComment): Unit = {
    comments.remove(comment)
    comment.setPost(null)
  }

  override def toString: String = s"Post: $id $id $title $comments."

}