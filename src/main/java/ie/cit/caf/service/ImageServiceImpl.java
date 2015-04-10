package ie.cit.caf.service;

import ie.cit.caf.domain.Image;
import ie.cit.caf.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author 
 * 
 * This class implements the interface for ImageService.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id & resolution
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class ImageServiceImpl implements ImageService {
	
	//instance variable that is updated via constructor DI
	ImageRepository imageRepository;

	//Autowire this object, using constructor DI.
	@Autowired
	public ImageServiceImpl (ImageRepository imageRepository)
	{
		this.imageRepository = imageRepository;
	}
		
	@Override
	public Image get(String id, String resolution) 
	{	
		return imageRepository.get(id,  resolution);
	}

	@Override
	public void save(Image image) 
	{
		imageRepository.save(image);	
	}

	@Override
	public void remove(Image image) 
	{
		imageRepository.remove(image);
	}

	@Override
	public List<Image> findAll() 
	{
		return imageRepository.findAll();
	}

}