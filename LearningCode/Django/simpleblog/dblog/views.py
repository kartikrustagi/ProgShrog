# Create your views here.
from django.shortcuts import render_to_response
from simpleblog.dblog.models import Blog
from django.shortcuts import get_object_or_404

def index(request):
    """Generate the context for th main summary page"""
    latest_blog_list = Blog.objects.all().order_by('-post_date')[:3]
    return render_to_response('index.html',{'latest_blog_list':latest_blog_list})

def readblog(request, blog_id):
    """In response to blod read request"""
    blog = get_object_or_404(Blog, pk=int(blog_id))
    return render_to_response('readBlog.html',{'blog': blog})
