/* 
   1.Here I reconstruct the Graph in the form of adjacency list
   2. Semaphores not required because the operations are associative in nature
   3. Built and tested on linux
   4. To compile:-
   	 gcc -D_REENTRANT hosoya.c -lpthread
       To Execute:-
         ./a.out input.txt  	 
*/   		


#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>
int result=0;

//Adjacency list representation of the graph

struct node_a
{
	char m[2];
	struct node_b *first;
	struct node_b *last;
	struct node_a *down;
	
};

typedef struct node_a node_a;
node_a *p=NULL;
node_a *head=NULL;

struct node_b
{
	char n[2];
	struct node_b *right;
};

typedef struct node_b node_b;


void insert_a(char *c)
{
	node_a *a=(node_a*)malloc(sizeof(node_a));
	a->m[0]=*(c+0);
	a->m[1]=*(c+1);
	a->first=NULL;
	a->last=NULL;
	a->down=NULL;
	
	if(p==NULL)
	{	
		head=a;
		p=a;
	}
	
	else
	{
		p->down=a;
		p=p->down;
	}	
}

void insert_b(node_a *k,char *c)
{
	node_b *b=(node_b*)malloc(sizeof(node_b));
	b->n[0]=*(c+0);
	b->n[1]=*(c+1);
	b->right=NULL;
		
	
	if(k->first==NULL)
		k->first=b;
		
		
	else
		k->last->right=b;
	
	k->last=b;
}

//This function adds new nodes to the graph
	
int search_add(char *ch)
{
	node_a *temp=head;
	
	while(temp!=NULL)
	{
		if((temp->m[0]==*(ch+0))&&(temp->m[1]==*(ch+1)))
		{
			break;
		}
		temp=temp->down;
	}
	
	if(temp==NULL)
	{
		insert_a(ch);
		temp=p;
	}
	
	char st[2];
	st[0]=*(ch+2);
	st[1]=*(ch+3);
	insert_b(temp,st);
}
		
//This function caculates n0. of k-matching for a perticular k

void* calc(void *l)
{
	int k=*((int*)l);
	node_a *a[k];
	node_b *b[k];
	
	a[0]=head;
	b[0]=head->first;
	
	int i=1;
	int c=0;
	int flag=0,j=0,x=1,flag2=0;
	
	while(a[0]!=NULL)
	{
		if(flag2==0)
			i=x;
		
		while(i<(k-1))
		{
			if(flag2==0)
				a[i]=a[i-1]->down;
			flag=0;
			do
			{
				
				j=0;
				for(j=0;j<i&&(a[i]!=NULL);j++)
				{
					if(((a[i]->m[0]==a[j]->m[0])&&(a[i]->m[1]==a[j]->m[1]))||((a[i]->m[0]==b[j]->n[0])&&(a[i]->m[1]==b[j]->n[1])))
					{
						a[i]=a[i]->down;
						j=-1;
					}
				}
				
				if(a[i]==NULL)
				{
					result+=c;
					return;
				}
				if((flag2==0)&&(a[0]!=NULL))
				{	
					b[i]=a[i]->first;
					
				}
				if(a[0]==NULL)
				{
					result+=c;
					return;
				}
				flag2=0;
				j=0;
				for(j=0;j<i&&(b[i]!=NULL);j++)
				{
					if(((b[i]->n[0]==a[j]->m[0])&&(b[i]->n[1]==a[j]->m[1]))||((b[i]->n[0]==b[j]->n[0])&&(b[i]->n[1]==b[j]->n[1])))
					{
						b[i]=b[i]->right;
						j=-1;
					}
				}
				
				if(b[i]==NULL)
				{
					a[i]=a[i]->down;
				}
				else
				{
					flag=1;
				}
			
			}while(flag!=1);
			++i;
		}
				
		a[i]=a[i-1]->down;
		b[i]=NULL;
		
		while(a[i]!=NULL)
		{
			j=0;
			for(j=0;j<i&&(a[i]!=NULL);j++)
			{
					
				if(((a[i]->m[0]==a[j]->m[0])&&(a[i]->m[1]==a[j]->m[1]))||((a[i]->m[0]==b[j]->n[0])&&(a[i]->m[1]==b[j]->n[1])))
				{
					a[i]=a[i]->down;
				}
			}
					
					
			if(a[i]==NULL)
				break;
				
			if(b[i]==NULL)
				b[i]=a[i]->first;			
				
			for(j=0;j<i&&(b[i]!=NULL);j++)
			{
				if(((b[i]->n[0]==a[j]->m[0])&&(b[i]->n[1]==a[j]->m[1]))||((b[i]->n[0]==b[j]->n[0])&&(b[i]->n[1]==b[j]->n[1])))
				{
					b[i]=b[i]->right;
					j=-1;
				}
			}
		
			if(b[i]!=NULL)
			{
				++c;
			}
		
			if((b[i]!=(a[i]->last))&&(b[i]!=NULL))
			{
				b[i]=b[i]->right;
			}
			
			else if((b[i]==NULL)||(b[i]==(a[i]->last)))
			{
				a[i]=a[i]->down;
				b[i]=NULL;
			}			
					
		}
		
		if((b[i-x]!=(a[i-x]->last))&&(b[i-x]!=NULL))
		{
			b[i-x]=b[i-x]->right;
			flag2=1;
		}
			
		else if((a[i]==NULL)||(b[i-x]==NULL)||(b[i-x]==(a[i-x]->last)))
		{
			if(((a[i-x]->down)==NULL)&&(x<k-1))
			{	
				++x;
			}
			a[i-x]=a[i-x]->down;
			flag2=1;
			if(a[i-x]!=NULL)
				b[i-x]=a[i-x]->first;
		}
					
	}
	result+=c;
}

		
int main(int argc,char**argv)
{
	FILE *fp;
	fp=fopen(argv[1],"r");
	int c1=0;
	
	char ch[5];
	
	while((fscanf(fp,"%s",ch))!=EOF)
	{
		search_add(ch);
		++c1;
	}
	
	
	pthread_t a_thread[c1-1];
	int i=c1;
	int tmp[c1-1];
	result+=1;
	result+=7;
	 
	while(i>1)
	{
		tmp[i-2]=i;
		pthread_create(&a_thread[i-2],NULL,calc,&tmp[i-2]);
		--i;
	}
	pthread_join(a_thread[i-1],NULL);
	printf("The Hosoya index of the input graph is %d \n",result);
	return(0);
}		



	
 
	


