from django.db import models

# Create your models here.
class Blog(models.Model):
     "Blog Database"
     title = models.CharField(max_length = 128)
     post_date = models.DateTimeField('date posted') 
     reply_to = models.EmailField() 
     content = models.TextField() 
     def rating(self):
         comments = self.commen_set.all()
         avg_rating = 0.0
         for c in comments:
            avg_rating += c.rating
         if comments.len > 0:
            avg_rating /= comments.len
            return "%.2f"%avg_rating
         else:
            return "(unrated)"
    
     RATING_CHOICES = (
                    (1, 'Lame'),
                    (2, 'Weak'),
                    (3, 'OK'),
                    (4, 'Nice'),
                    (5, 'Rocks'),
            )

     def __str__(self):
        return "Blog(title = '%s')"%self.title

     class Admin:
        pass

class Comment(models.Model):
    content = models.TextField(max_length = 256)
    rating = models.IntegerField(choices = Blog.RATING_CHOICES)
    in_reference_to = models.ForeignKey(Blog)

    def ratingText(self):
        return Blog.RATIN_CHOICES[self.rating-1][1]
    
    def __str__ (self):
        return "Comment(content = '%s', rating = %s)"%(self.content,str(self.rating))
