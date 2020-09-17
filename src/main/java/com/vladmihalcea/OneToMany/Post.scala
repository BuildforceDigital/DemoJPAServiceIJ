package com.vladmihalcea.OneToMany

import java.{util => ju}

import jakarta.persistence.{CascadeType, Entity, GeneratedValue, GenerationType, Id, OneToMany, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.beans.BeanProperty

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = Post.tableName, schema = Post.schemaName)
class Post(@BeanProperty val title: String) {
  @OneToMany(mappedBy = Post.tableName, cascade = Array(CascadeType.ALL), orphanRemoval = true)
  // Use Java collection types instead of Scala ones to make JPA happy
  final private val comments: ju.List[PostComment] = new ju.ArrayList[PostComment]

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id = 0L

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

  // Default constructor for persistence providers
  // No public visibility required
  private def this() = this(null)

}

object Post {
  final val tableName = "post"
  final val schemaName ="OLINGO"
}