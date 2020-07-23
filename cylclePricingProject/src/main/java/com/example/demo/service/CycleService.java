package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.Entity.ChainAluminiumEntity;
import com.example.demo.Entity.ChainSilverEntity;
import com.example.demo.Entity.FrameCantiLeverEntity;
import com.example.demo.Entity.FrameCrossEntity;
import com.example.demo.Entity.FrameDiamodEntity;
import com.example.demo.Entity.FrameProneEntity;
import com.example.demo.Entity.HandleAeroEntity;
import com.example.demo.Entity.HandleDropEntity;
import com.example.demo.Entity.HandleFlatEntity;
import com.example.demo.Entity.HandleRiserEntity;
import com.example.demo.Entity.SeatCusionEntity;
import com.example.demo.Entity.SeatOrdinaryEntity;
import com.example.demo.Entity.WheelRimEntity;
import com.example.demo.Entity.WheelSpokesEntity;
import com.example.demo.Entity.WheelTubeEntity;
import com.example.demo.Entity.WheelTyreEntity;
import com.example.demo.repository.ChainAluminiumRepository;
import com.example.demo.repository.ChainSilverRepository;
import com.example.demo.repository.FrameCantileverRepository;
import com.example.demo.repository.FrameCrossRepository;
import com.example.demo.repository.FrameDiamondRepository;
import com.example.demo.repository.FrameProneRepository;
import com.example.demo.repository.HandleAeroRepository;
import com.example.demo.repository.HandleDropRepository;
import com.example.demo.repository.HandleFlatRepository;
import com.example.demo.repository.HandleRiserRepository;
import com.example.demo.repository.SeatCusionRepository;
import com.example.demo.repository.SeatOrdinaryRepository;
import com.example.demo.repository.WheelRimRepository;
import com.example.demo.repository.WheelSpokeRepository;
import com.example.demo.repository.WheelTyreRepository;
import com.example.demo.repository.WheeltubeRepository;
import com.fasterxml.jackson.databind.util.NameTransformer.Chained;


@Service
public class CycleService implements CyclePriceEngine{
	long millis=System.currentTimeMillis();  
	Date date=new Date(millis);  
	
	@Autowired
	private WheelTyreRepository wheelTyreRepository;
	
	@Autowired
	private WheeltubeRepository wheeltubeRepository;
	
	@Autowired
	private WheelSpokeRepository wheelSpokeRepository;
	 
	@Autowired
	private WheelRimRepository wheelRimRepository;
	
	@Autowired
	private SeatCusionRepository seatCusionRepository;
	
	@Autowired
	private SeatOrdinaryRepository seatOrdinaryRepository;
	
	@Autowired
	private HandleDropRepository handleDropRepository;
	
	@Autowired
	private HandleAeroRepository handleAeroRepository;
	
	@Autowired
	private HandleFlatRepository handleFlatRepository;
	
	@Autowired
	private HandleRiserRepository handleRiserRepository;
	
	@Autowired
	private FrameCantileverRepository frameCantileverRepository;
	
	@Autowired
	private FrameCrossRepository frameCrossRepository;
	
	@Autowired
	private FrameDiamondRepository frameDiamondRepository;
	
	@Autowired
	private FrameProneRepository frameProneRepository;
	
	@Autowired
	private ChainAluminiumRepository chainAluminiumRepository;
	
	@Autowired
	private ChainSilverRepository chainSilverRepository; 
	
	@Autowired
	private CycleResponseEntity cycleResponseEntity;
	
	@Override
	public CycleResponseEntity calculateCyclePrice(Frame frame, HandleandBreak handleandBreak, Seating seating, Wheel wheel,
			ChainAssembly chainAssembly) {
		Long wheelPrice = setWheelValues(wheel);
		Long frameprice = setFrameValues(frame);
		Long handlePrice = setHandleValues(handleandBreak);
		Long seatPrice = setSeatValues(seating);
		Long chainPrice = setChainValues(chainAssembly);
		cycleResponseEntity.setChainPrice(chainPrice);
		cycleResponseEntity.setFrameprice(frameprice);
		cycleResponseEntity.setHandlePrice(handlePrice);
		cycleResponseEntity.setSeatPrice(seatPrice);
		cycleResponseEntity.setWheelPrice(wheelPrice);
		cycleResponseEntity.setCycleTotalPice(frameprice+handlePrice+seatPrice+chainPrice+wheelPrice);
		
		return cycleResponseEntity;
	}
	
	public Long setFrameValues(Frame frame) {
		FrameCantiLeverEntity frameCantiLeverEntity = new FrameCantiLeverEntity();
		FrameCrossEntity frameCrossEntity = new FrameCrossEntity();
		FrameDiamodEntity frameDiamodEntity= new FrameDiamodEntity();
		FrameProneEntity frameProneEntity = new FrameProneEntity();
		long value=0;
		Long frameAmount = new Long(value);
		Long cantiAmount = new Long(value);
		Long crossAmount = new Long(value);
		Long diamondAmount = new Long(value);
		Long proneAmount = new Long(value);
		if(Objects.nonNull(frame)) {
			if(!StringUtils.isEmpty(frame.getCantiLever())) {
				frameCantiLeverEntity= frameCantileverRepository.searchPriceByBetweenDate(frame.getCantiLeverDate());
			}
			if(!StringUtils.isEmpty(frame.getCross())) {
				frameCrossEntity = frameCrossRepository.searchPriceByBetweenDate(frame.getCrossDate());
			}if(!StringUtils.isEmpty(frame.getDiamond())) {
				frameDiamodEntity = frameDiamondRepository.searchPriceByBetweenDate(frame.getDiamondDate());
			}if(!StringUtils.isEmpty(frame.getProne())) {
				frameProneEntity = frameProneRepository.searchPriceByBetweenDate(frame.getProneDate());
			}
		}else {
			if(Objects.nonNull(frameCantileverRepository.searchPriceByBetweenDate(date))) {
			frameCantiLeverEntity=frameCantileverRepository.searchPriceByBetweenDate(date);
		}}
		if(Objects.nonNull(frameCantiLeverEntity.getPrice())) {
			cantiAmount=frameCantiLeverEntity.getPrice();
		}if(Objects.nonNull(frameCrossEntity.getPrice())) {
			crossAmount=frameCrossEntity.getPrice();
		}if(Objects.nonNull(frameDiamodEntity.getPrice())) {
			diamondAmount=frameDiamodEntity.getPrice();
		}if(Objects.nonNull((frameProneEntity.getPrice()))) {
			proneAmount=frameProneEntity.getPrice();
		}
		frameAmount=proneAmount+diamondAmount+cantiAmount+crossAmount;
		 return frameAmount;
		
	}
	public Long setHandleValues(HandleandBreak handleandBreak) {
		HandleRiserEntity handleRiserEntity= new HandleRiserEntity();
		HandleAeroEntity handleAeroEntity = new HandleAeroEntity();
		HandleFlatEntity handleFlatEntity = new HandleFlatEntity();
		HandleDropEntity handleDropEntity = new HandleDropEntity();
		long value=0;
		Long handleAmount = new Long(value);
		Long aeroAmount = new Long(value);
		Long dropAmount = new Long(value);
		Long flatAmount = new Long(value);
		Long riserAmount = new Long(value);
		if(Objects.nonNull(handleandBreak)) {
			if(!StringUtils.isEmpty(handleandBreak.getAero())) {
				handleAeroEntity= handleAeroRepository.searchPriceByBetweenDate(handleandBreak.getAeroDate());
			}
			if(!StringUtils.isEmpty(handleandBreak.getDrop())) {
				handleDropEntity = handleDropRepository.searchPriceByBetweenDate(handleandBreak.getDropDate());
			}if(!StringUtils.isEmpty(handleandBreak.getFlat())) {
				handleFlatEntity = handleFlatRepository.searchPriceByBetweenDate(handleandBreak.getFlatDate());
			}if(!StringUtils.isEmpty(handleandBreak.getRiser())) {
				handleRiserEntity = handleRiserRepository.searchPriceByBetweenDate(handleandBreak.getRiserDate());
			}
		}else {
			if(Objects.nonNull(handleAeroRepository.searchPriceByBetweenDate(handleandBreak.getAeroDate()))) {
			handleAeroEntity= handleAeroRepository.searchPriceByBetweenDate(handleandBreak.getAeroDate());
			}
		}
		if(Objects.nonNull(handleAeroEntity.getPrice())) {
			aeroAmount=handleAeroEntity.getPrice();
		}if(Objects.nonNull(handleDropEntity.getPrice())) {
			dropAmount=handleDropEntity.getPrice();
		}if(Objects.nonNull(handleFlatEntity.getPrice())) {
			flatAmount=handleFlatEntity.getPrice();
		}if(Objects.nonNull((handleRiserEntity.getPrice()))) {
			riserAmount=handleRiserEntity.getPrice();
		}
		handleAmount=aeroAmount+dropAmount+flatAmount+riserAmount;
		return handleAmount;
		
	}
	public Long setSeatValues(Seating seating) {
		SeatCusionEntity seatCusionEntity= new SeatCusionEntity();
		SeatOrdinaryEntity seatOrdinaryEntity = new SeatOrdinaryEntity();
		long value=0;
		Long seatAmount = new Long(value);
		Long cusionAmount = new Long(value);
		Long ordinaryAmount = new Long(value);
		if(Objects.nonNull(seating)) {
			if(!StringUtils.isEmpty(seating.getCusion())) {
				seatCusionEntity= seatCusionRepository.searchPriceByBetweenDate(seating.getCusionDate());
			}
			if(!StringUtils.isEmpty(seating.getLeather())) {
				seatOrdinaryEntity = seatOrdinaryRepository.searchPriceByBetweenDate(seating.getLeatherDate());
			}
		}else {
			if(Objects.nonNull(seatCusionRepository.searchPriceByBetweenDate(seating.getCusionDate()))){
			seatCusionEntity= seatCusionRepository.searchPriceByBetweenDate(seating.getCusionDate());
		}}
		if(Objects.nonNull(seatCusionEntity.getPrice())) {
			cusionAmount=seatCusionEntity.getPrice();
		}if(Objects.nonNull(seatOrdinaryEntity.getPrice())) {
			ordinaryAmount=seatOrdinaryEntity.getPrice();
		}
		seatAmount=cusionAmount+ordinaryAmount;
		return seatAmount;
		
	}
	public Long setWheelValues(Wheel wheel) {
		WheelTyreEntity wheelTyreEntity= new WheelTyreEntity();
		WheelRimEntity wheelRimEntity = new WheelRimEntity();
		WheelSpokesEntity wheelSpokesEntity = new WheelSpokesEntity();
		WheelTubeEntity wheelTubeEntity= new WheelTubeEntity();
		long value=0;
		Long rimAmount=new Long(value) ;
		Long tyreAmount=new Long(value) ;
		Long spokeAmount=new Long(value) ;
		Long tubeAmount=new Long(value) ;
		
		Long wheelAmount = new Long(value);
		if(Objects.nonNull(wheel)) {
			if(!StringUtils.isEmpty(wheel.getRim())) {
				wheelRimEntity= wheelRimRepository.searchPriceByBetweenDate(wheel.getTyreDate());
			}
			if(!StringUtils.isEmpty(wheel.getSpokes())) {
				wheelSpokesEntity = wheelSpokeRepository.searchPriceByBetweenDate(wheel.getSpokesDate());
			}if(!StringUtils.isEmpty(wheel.getTube())) {
				wheelTubeEntity = wheeltubeRepository.searchPriceByBetweenDate(wheel.getSpokesDate());
			}if(!StringUtils.isEmpty(wheel.getTyre())) {
				wheelTyreEntity = wheelTyreRepository.searchPriceByBetweenDate(wheel.getTyreDate());
			}
		}else {
			if(Objects.nonNull(wheelTyreRepository.searchPriceByBetweenDate(date))){
			wheelTyreEntity=wheelTyreRepository.searchPriceByBetweenDate(date);
		}}if(Objects.nonNull(wheelRimEntity.getPrice())) {
			rimAmount=wheelRimEntity.getPrice();
		}if(Objects.nonNull(wheelSpokesEntity.getPrice())) {
			spokeAmount=wheelSpokesEntity.getPrice();
		}if(Objects.nonNull(wheelTubeEntity.getPrice())) {
			tubeAmount=wheelTubeEntity.getPrice();
		}if(Objects.nonNull((wheelTyreEntity.getPrice()))) {
			tyreAmount=wheelTyreEntity.getPrice();
		}
		wheelAmount=tyreAmount+rimAmount+spokeAmount+tubeAmount;
		return wheelAmount;
		
	}
	public Long setChainValues(ChainAssembly chainAssembly) {
		ChainAluminiumEntity chainAluminiumEntity= new ChainAluminiumEntity();
		ChainSilverEntity chainSilverEntity = new ChainSilverEntity();
		long value=0;
		Long chainAmount = new Long(value);
		Long alumiAmount = new Long(value);
		Long silverAmount = new Long(value);
		if(Objects.nonNull(chainAssembly)) {
			if(!StringUtils.isEmpty(chainAssembly.getCampagnolo())) {
				chainAluminiumEntity= chainAluminiumRepository.searchPriceByBetweenDate(chainAssembly.getCampagnoloDate());
			}
			if(!StringUtils.isEmpty(chainAssembly.getRohloff())) {
				chainSilverEntity = chainSilverRepository.searchPriceByBetweenDate(chainAssembly.getRohloffDate());
			}
		}else {
			if(Objects.nonNull(chainAluminiumRepository.searchPriceByBetweenDate(chainAssembly.getCampagnoloDate()))) {
			chainAluminiumEntity= chainAluminiumRepository.searchPriceByBetweenDate(chainAssembly.getCampagnoloDate());
		}}
		if(Objects.nonNull(chainAluminiumEntity.getPrice())) {
			alumiAmount=chainAluminiumEntity.getPrice();
		}if(Objects.nonNull(chainSilverEntity.getPrice())) {
			silverAmount=chainSilverEntity.getPrice();
		}
		chainAmount=alumiAmount+silverAmount;
		return chainAmount;
		
	}
	
}
